package br.com.tangerino.tangerino.controller;

import br.com.tangerino.tangerino.model.dtos.LoginFilterDto;
import br.com.tangerino.tangerino.model.entity.Usuario;
import br.com.tangerino.tangerino.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginFilterDto login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

        var usuario = (Usuario) authentication.getPrincipal();

        return ResponseEntity.ok(tokenService.gerarToken(usuario));
    }
}
