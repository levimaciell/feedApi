package com.grupofive.demo.security_auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.grupofive.demo.User.entities.User;

@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer("feedApi")//quem criou o token
                .withSubject(user.getUsername())//quem recebe o token
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);//para assinar o token com o algoritmo e transformá-lo em string
            
            return token;
        } 
        catch (JWTCreationException e) {
            throw new TokenServiceException("Error while generating token", HttpStatus.UNAUTHORIZED);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("feedApi")
                .build()
                .verify(token)
                .getSubject();

        }
        catch(JWTVerificationException e){
            throw new TokenServiceException("Error while validating token", HttpStatus.UNAUTHORIZED);
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
