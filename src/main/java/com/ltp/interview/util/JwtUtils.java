package com.ltp.interview.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JwtUtils {

    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;
    @Value("${interview.jwt.secret}")
    private String secret;
    @Value("${interview.jwt.expires}")
    private long expires;

    public DecodedJWT verify(final String token) {
        try{
            return jwtVerifier.verify(token);
        } catch (JWTVerificationException _){}
        return null;
    }

    public String generateToken(final String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(Instant.now().plusMillis(expires))
                .sign(algorithm);
    }

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(secret);
        jwtVerifier = JWT.require(algorithm).build();
    }

}
