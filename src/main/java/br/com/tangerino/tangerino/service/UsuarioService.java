package br.com.tangerino.tangerino.service;

import br.com.tangerino.tangerino.configurations.BusinessException;
import br.com.tangerino.tangerino.model.dtos.UsuarioDto;
import br.com.tangerino.tangerino.model.dtos.UsuarioFilterDto;
import br.com.tangerino.tangerino.model.entity.Usuario;
import br.com.tangerino.tangerino.model.mappers.UsuarioFilterMapper;
import br.com.tangerino.tangerino.model.mappers.UsuarioMapper;
import br.com.tangerino.tangerino.model.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final UsuarioFilterMapper mapperFilter;

    public UsuarioDto obterPorId(Long id) {

        try {
            Usuario entity = repository.findById(id).orElseThrow();
            return mapper.toDto(entity);

        } catch (BusinessException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UsuarioDto salvar(UsuarioFilterDto dto) {
        try {
            Usuario entity = mapperFilter.toEntity(dto);
            Usuario usuario = repository.save(entity);
            return mapper.toDto(usuario);

        } catch (BusinessException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }
}
