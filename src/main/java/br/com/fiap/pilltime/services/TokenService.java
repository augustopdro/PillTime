package br.com.fiap.pilltime.services;

import br.com.fiap.pilltime.dtos.LoginDTO;
import br.com.fiap.pilltime.dtos.TokenDTO;
import br.com.fiap.pilltime.models.Usuario;
import br.com.fiap.pilltime.repositories.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Value("${jwt.secret}")
    String secret;

    public TokenDTO generateToken(LoginDTO credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var token = JWT.create()
                .withSubject(credencial.email())
                .withExpiresAt(Instant.now().plus(24, ChronoUnit.HOURS))
                .withIssuer("PillTime")
                .sign(alg);

        return new TokenDTO(token, "JWT", "Bearer");
    }

    public Usuario valideAndGetUserBy(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email =  JWT.require(alg)
                .withIssuer("PillTime")
                .build()
                .verify(token)
                .getSubject()
                ;

        return usuarioRepository.buscarEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

}
