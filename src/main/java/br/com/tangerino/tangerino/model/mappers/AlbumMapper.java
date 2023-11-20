package br.com.tangerino.tangerino.model.mappers;

import br.com.tangerino.tangerino.model.dtos.AlbumDto;
import br.com.tangerino.tangerino.model.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlbumMapper {
    AlbumDto toDto(Album entity);

    List<AlbumDto> toDto(List<Album> entity);

    Album toEntity(AlbumDto dto);

    List<Album> toEntity(List<AlbumDto> dto);

}
