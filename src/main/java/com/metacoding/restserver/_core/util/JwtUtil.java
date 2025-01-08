package com.metacoding.restserver._core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.metacoding.restserver._core.auth.LoginUser;
import com.metacoding.restserver.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JwtUtil {

    // 컴퍼넌트 스캔시에 @Value가 발동하고, 해당 값은 application.properties에서 가져온다.
    @Value("${var.jwt.secret}")
    private String secret;

    public final Long EXPIRATION_TIME = 1000*60*60*24*2L;

    public String create(User User) {
        String jwt = JWT.create()
                .withSubject("title")
                .withClaim("id", User.getId())
                .withClaim("username", User.getUsername())
                .withExpiresAt(Instant.now().plusMillis(EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secret));
        return "Bearer " + jwt;
    }

    public LoginUser verify(String jwt)
            throws SignatureVerificationException, TokenExpiredException, JWTDecodeException {
        jwt = jwt.replace("Bearer ", "");

        // JWT를 검증한 후, 검증이 완료되면, header, payload를 base64로 복호화함.
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(secret))
                .build().verify(jwt);

        int id = decodedJWT.getClaim("id").asInt();
        String username = decodedJWT.getClaim("username").asString();

        return new LoginUser(id, username, jwt);
    }
}