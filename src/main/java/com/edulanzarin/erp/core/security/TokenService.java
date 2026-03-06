package com.edulanzarin.erp.core.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.edulanzarin.erp.comum.usuario.model.Usuario;

@Service
public class TokenService {

    // puxa chave do application.yml
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            // o algoritmo usa a chave secreta pra assinar o token
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("contiva-api")
                    .withSubject(usuario.getEmail()) // o assunto principal é o email do usuario
                    .withClaim("nome", usuario.getNome())
                    .withExpiresAt(genExpirationDate()) // validade do cracha
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("contiva-api")
                    .build()
                    .verify(token)
                    .getSubject(); // devolve o email que ta dentro do token
        } catch (JWTVerificationException exception) {
            return ""; // devolve vazio e spring barra
        }
    }

    private Instant genExpirationDate() {
        // se usuario ficar 2h sem ações, desloga
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
