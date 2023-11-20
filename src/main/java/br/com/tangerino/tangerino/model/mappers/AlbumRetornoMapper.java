package br.com.tangerino.tangerino.model.mappers;

import br.com.tangerino.tangerino.model.dtos.AlbumRetornoDto;
import br.com.tangerino.tangerino.model.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlbumRetornoMapper {
    AlbumRetornoDto toDto(Album entity);

    List<AlbumRetornoDto> toDto(List<Album> entity);

    Album toEntity(AlbumRetornoDto dto);

    List<Album> toEntity(List<AlbumRetornoDto> dto);

}
