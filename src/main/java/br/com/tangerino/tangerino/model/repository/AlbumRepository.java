package br.com.tangerino.tangerino.model.repository;

import br.com.tangerino.tangerino.model.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
