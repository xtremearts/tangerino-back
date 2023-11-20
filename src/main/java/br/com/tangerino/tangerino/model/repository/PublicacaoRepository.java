package br.com.tangerino.tangerino.model.repository;

import br.com.tangerino.tangerino.model.entity.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

}
