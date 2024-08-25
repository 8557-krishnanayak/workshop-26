package com.godigit.taskAppivation.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenUtility {

    private final static String SECRET = "poytrewq1234567890!@#$%^&*()";

    public String getToken(Long id) {
        return JWT.create()
                .withClaim("user_id", id)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public Long decodeAsLong(String token) {

        return JWT
                .require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getClaim("user_id").asLong();
    }
}
