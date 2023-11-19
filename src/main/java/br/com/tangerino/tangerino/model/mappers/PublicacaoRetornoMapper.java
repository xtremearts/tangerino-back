package br.com.tangerino.tangerino.model.mappers;

import br.com.tangerino.tangerino.model.dtos.PublicacaoRetornoDto;
import br.com.tangerino.tangerino.model.entity.Publicacao;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PublicacaoRetornoMapper  {
    PublicacaoRetornoDto toDto(Publicacao entity) throws IOException;

    List<PublicacaoRetornoDto> toDto(List<Publicacao> entity);

    Publicacao toEntity(PublicacaoRetornoDto dto);
}
