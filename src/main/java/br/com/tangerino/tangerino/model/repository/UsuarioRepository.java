package br.com.tangerino.tangerino.model.repository;

import br.com.tangerino.tangerino.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
