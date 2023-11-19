package br.com.tangerino.tangerino.service;

import br.com.tangerino.tangerino.configurations.BusinessException;
import br.com.tangerino.tangerino.model.dtos.PublicacaoDto;
import br.com.tangerino.tangerino.model.dtos.PublicacaoRetornoDto;
import br.com.tangerino.tangerino.model.entity.Publicacao;
import br.com.tangerino.tangerino.model.entity.Usuario;
import br.com.tangerino.tangerino.model.mappers.PublicacaoMapper;
import br.com.tangerino.tangerino.model.mappers.PublicacaoRetornoMapper;
import br.com.tangerino.tangerino.model.repository.PublicacaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final PublicacaoMapper mapper;
    private final PublicacaoRetornoMapper publicacaoRetornoMapper;
    private final ImagensService imagensService;

    @Transactional(propagation = Propagation.REQUIRED)
    public PublicacaoDto salvar(MultipartFile arquivo, String descricao) {

        Usuario usuarioRecebidoToken = getUsuario();
        try {
            Publicacao entity = new Publicacao();
            entity.setUsuario(usuarioRecebidoToken);
            entity.setDescricao(descricao);

            Publicacao publicacao = repository.save(entity);
            this.salvarImagemPublicacao(arquivo, publicacao.getId());

            return mapper.toDto(publicacao);

        } catch (BusinessException | IOException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    private void salvarImagemPublicacao(MultipartFile arquivo, Long id) throws IOException {
        try {
            if (!Objects.equals(arquivo.getContentType(), MediaType.IMAGE_JPEG_VALUE) && !Objects.equals(arquivo.getContentType(), MediaType.IMAGE_PNG_VALUE)) {
                throw new BusinessException("Permitido apenas os formatos de imagem: jpg ou png.");
            }

            imagensService.salvarArquivo(arquivo, id.toString());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new IOException(e.getMessage());
        }
    }

    public List<PublicacaoRetornoDto> obterTodos() {
        List<Publicacao> publicacaos = repository.findAll();
        List<PublicacaoRetornoDto> dto = publicacaoRetornoMapper.toDto(publicacaos);

        int i = 0;
        for (PublicacaoRetornoDto publicacao: dto) {
                dto.get(i).setImagem(imagensService.obterArquivoPorId());
            i++;
        }
        return dto;
    }
}
