package br.com.tangerino.tangerino.service;

import br.com.tangerino.tangerino.configurations.BusinessException;
import br.com.tangerino.tangerino.model.dtos.AlbumDto;
import br.com.tangerino.tangerino.model.dtos.AlbumRetornoDto;
import br.com.tangerino.tangerino.model.entity.Album;
import br.com.tangerino.tangerino.model.mappers.AlbumMapper;
import br.com.tangerino.tangerino.model.mappers.AlbumRetornoMapper;
import br.com.tangerino.tangerino.model.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class AlbumService extends AppService {
    private final AlbumRepository repository;

    private final AlbumMapper mapper;
    private final AlbumRetornoMapper mapperRetorno;
    private final ImagensService imagensService;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<AlbumDto> salvar(List<MultipartFile> arquivos) {

        try {
            List<Album> listAlbum = new ArrayList<>();

            for (MultipartFile arquivo : arquivos) {
                Album entity = new Album();
                entity.setUsuario(getUsuarioToken());
                this.tratarTipoArquivoImagem(entity, arquivo);

                Album album = repository.save(entity);

                listAlbum.add(album);

                String noArquivo = "album_" + album.getId() + album.getExtensaoArquivo();

                imagensService.salvarArquivo(arquivo, noArquivo);
            }

            return mapper.toDto(listAlbum);

        } catch (BusinessException | IOException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    private void tratarTipoArquivoImagem(Album entity, MultipartFile arquivo) {
        if (!Objects.equals(arquivo.getContentType(), MediaType.IMAGE_JPEG_VALUE) && !Objects.equals(arquivo.getContentType(), MediaType.IMAGE_PNG_VALUE)) {
            throw new BusinessException("Permitido apenas os formatos de imagem: jpg ou png.");
        }

        if (Objects.equals(arquivo.getContentType(), MediaType.IMAGE_JPEG_VALUE)) entity.setExtensaoArquivo(".jpg");
        if (Objects.equals(arquivo.getContentType(), MediaType.IMAGE_PNG_VALUE)) entity.setExtensaoArquivo(".png");
    }

    public List<AlbumRetornoDto> obterTodos() {
        List<Album> album = repository.findAll(Sort.by(Sort.Direction.DESC, "dtCriacao"));
        List<AlbumRetornoDto> dto = mapperRetorno.toDto(album);

        int i = 0;
        for (Album alb : album) {
            String noArquivo = "album_" + alb.getId() + alb.getExtensaoArquivo();
            dto.get(i).setImagem(imagensService.obterArquivoPorId(noArquivo));
            i++;
        }
        return dto;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(Long id) {
        try {
            Album entity = repository.findById(id).orElseThrow();

            if(!entity.getUsuario().getId().equals(getUsuarioToken().getId())) {
                throw new BusinessException("Exclusão não permitida, o usuário só pode excluir suas fotos");
            }
            repository.delete(entity);

        } catch (BusinessException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }
}
