package br.com.tangerino.tangerino.controller;

import br.com.tangerino.tangerino.model.dtos.LoginDto;
import br.com.tangerino.tangerino.model.entity.Usuario;
import br.com.tangerino.tangerino.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "v1/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String logar(@RequestBody LoginDto login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

        var usuario = (Usuario) authentication.getPrincipal();

        return tokenService.gerarToken(usuario);

    }




}
