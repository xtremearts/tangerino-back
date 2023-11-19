package br.com.tangerino.tangerino.service;

import br.com.tangerino.tangerino.model.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String gerarToken(Usuario usuario) {
        return JWT.create()
                .withIssuer("login")
                .withSubject(usuario.getEmail())
                .withClaim("id", usuario.getId())
                .withClaim("nome", usuario.getNome())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(60)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("secret"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("secret"))
                .withIssuer("login")
                .build().verify(token).getSubject();

    }
}
