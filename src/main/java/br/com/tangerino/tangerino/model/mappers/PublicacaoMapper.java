package br.com.tangerino.tangerino.model.mappers;

import br.com.tangerino.tangerino.model.dtos.PublicacaoDto;
import br.com.tangerino.tangerino.model.entity.Publicacao;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PublicacaoMapper {
    PublicacaoDto toDto(Publicacao entity);

    Publicacao toEntity(PublicacaoDto dto);
}
