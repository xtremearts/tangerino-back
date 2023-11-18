package br.com.tangerino.tangerino.service;

import br.com.tangerino.tangerino.configurations.BusinessException;
import br.com.tangerino.tangerino.model.dtos.PublicacaoDto;
import br.com.tangerino.tangerino.model.entity.Publicacao;
import br.com.tangerino.tangerino.model.entity.Usuario;
import br.com.tangerino.tangerino.model.mappers.PublicacaoMapper;
import br.com.tangerino.tangerino.model.repository.PublicacaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@AllArgsConstructor
public class PublicacaoService extends AppService {
    private final PublicacaoRepository repository;
    private final PublicacaoMapper mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public PublicacaoDto salvar(MultipartFile arquivo, String descricao) {

        Usuario usuarioRecebidoToken = getUsuario();
        try {
            Publicacao entity = new Publicacao();
            entity.setUsuario(usuarioRecebidoToken);
            entity.setDescricao(descricao);

            Publicacao publicacao = repository.save(entity);
            return mapper.toDto(publicacao);

        } catch (BusinessException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }
}
