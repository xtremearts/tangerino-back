package br.com.tangerino.tangerino.service;

import br.com.tangerino.tangerino.model.entity.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    public Usuario getUsuarioToken() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
