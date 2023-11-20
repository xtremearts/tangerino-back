package br.com.tangerino.tangerino.model.mappers;

import br.com.tangerino.tangerino.model.dtos.UsuarioFilterDto;
import br.com.tangerino.tangerino.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioFilterMapper {
    Usuario toEntity(UsuarioFilterDto dto);
}
