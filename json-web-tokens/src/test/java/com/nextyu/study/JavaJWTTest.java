package com.nextyu.study;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * created on 2017-03-31 10:11
 *
 * @author nextyu
 */
public class JavaJWTTest {
    @Test
    public void testCreateAndSignAToken() throws Exception {
        // header.payload.signature
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("typ", "JWT");
        headerClaims.put("alg", "HS256");

        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create().withHeader(headerClaims)
                .withIssuer("nextyu").withSubject("").withExpiresAt(new DateTime().plusSeconds(20).toDate())
                .withClaim("userId", 1)
                .sign(algorithm);
        System.out.println(token);

    }

    @Test
    public void testVerifyAndSignAToken() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE0OTA5Mjk5ODgsInN1YiI6IiIsInVzZXJJZCI6MSwiaXNzIjoibmV4dHl1In0.gd-cj0DorAdY1svgjvo2O-vlWI0_2mm6wB8qag9Baoo";
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("nextyu")
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);
        System.out.println(jwt.getIssuer());
        System.out.println(jwt.getClaim("userId").asInt());
    }
}
