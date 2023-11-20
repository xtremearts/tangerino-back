package br.com.tangerino.tangerino.service;

import br.com.tangerino.tangerino.configurations.BusinessException;
import br.com.tangerino.tangerino.model.dtos.PublicacaoDto;
import br.com.tangerino.tangerino.model.dtos.PublicacaoRetornoDto;
import br.com.tangerino.tangerino.model.entity.Comentario;
import br.com.tangerino.tangerino.model.entity.Publicacao;
import br.com.tangerino.tangerino.model.entity.Usuario;
import br.com.tangerino.tangerino.model.mappers.PublicacaoMapper;
import br.com.tangerino.tangerino.model.mappers.PublicacaoRetornoMapper;
import br.com.tangerino.tangerino.model.repository.ComentariosRepository;
import br.com.tangerino.tangerino.model.repository.PublicacaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class PublicacaoService extends AppService {
    private final PublicacaoRepository repository;
    private final ComentariosRepository repositoryComentario;

    private final PublicacaoMapper mapper;
    private final PublicacaoRetornoMapper publicacaoRetornoMapper;
    private final ImagensService imagensService;

    @Transactional(propagation = Propagation.REQUIRED)
    public PublicacaoDto salvar(MultipartFile arquivo, String descricao) {

        Usuario usuarioRecebidoToken = getUsuarioToken();
        try {
            Publicacao entity = new Publicacao();
            entity.setUsuario(usuarioRecebidoToken);
            entity.setDescricao(descricao);

            //Impede de forma segura que formatos diferentes sejam salvos na aplicação
            tratarTipoArquivoImagem(entity, arquivo);

            Publicacao publicacao = repository.save(entity);

            String noArquivo = publicacao.getId() + publicacao.getExtensaoArquivo();

            imagensService.salvarArquivo(arquivo, noArquivo);

            return mapper.toDto(publicacao);

        } catch (BusinessException | IOException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    private void tratarTipoArquivoImagem(Publicacao entity, MultipartFile arquivo) {

        if (!Objects.equals(arquivo.getContentType(), MediaType.IMAGE_JPEG_VALUE) && !Objects.equals(arquivo.getContentType(), MediaType.IMAGE_PNG_VALUE)) {
            throw new BusinessException("Permitido apenas os formatos de imagem: jpg ou png.");
        }

        if (Objects.equals(arquivo.getContentType(), MediaType.IMAGE_JPEG_VALUE)) entity.setExtensaoArquivo(".jpg");
        if (Objects.equals(arquivo.getContentType(), MediaType.IMAGE_PNG_VALUE)) entity.setExtensaoArquivo(".png");
    }


    public List<PublicacaoRetornoDto> obterTodos() {
        List<Publicacao> publicacaos = repository.findAll(Sort.by(Sort.Direction.DESC, "dtCriacao"));
        List<PublicacaoRetornoDto> dto = publicacaoRetornoMapper.toDto(publicacaos);

        int i = 0;
        for (Publicacao pub: publicacaos) {
                String noArquivo = pub.getId() + pub.getExtensaoArquivo();
                dto.get(i).setImagem(imagensService.obterArquivoPorId(noArquivo));
            i++;
        }
        return dto;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(Long id) {
        try {
            Publicacao entity = repository.findById(id).orElseThrow();

            if(!entity.getUsuario().getId().equals(getUsuarioToken().getId())) {
                throw new BusinessException("Exclusão não permitida, o usuário só pode excluir suas publicações");
            }

            if(entity.getComentarios().size() > 0) {
                repositoryComentario.deleteAll(entity.getComentarios());
            }

            repository.delete(entity);

        } catch (BusinessException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }
}
