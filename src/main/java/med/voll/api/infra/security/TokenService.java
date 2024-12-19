package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.domain.users.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFecha())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Instant generarFecha() {
        // Ajusta la zona horaria a la de México
        return LocalDateTime.now().plusMinutes(10).atZone(ZoneId.of("America/Mexico_City")).toInstant();
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token no puede ser nulo");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (TokenExpiredException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "El token ha expirado", e);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error al verificar el token", e);
        }
    }
}












