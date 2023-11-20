package br.com.tangerino.tangerino.model.repository;

import br.com.tangerino.tangerino.model.entity.Comentario;
import br.com.tangerino.tangerino.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentariosRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByUsuarioOrderByDtCriacaoDesc(Usuario usuario);

    @Query(value = "" +
            "select c " +
            "from Comentario c " +
            "where c.publicacao.id = :idPublicacao " +
            "order by c.dtCriacao desc"
    )
    List<Comentario> obterPorPublicacao(Long idPublicacao);
}
