/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.business.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    /** Value secret key. */
    @Value("${jwt.secret}")
    private String                            secret;

    /** Value expiration. */
    @Value("${jwt.expiration}")
    private Long                              expiration;

    private static final GzipCompressionCodec COMPRESSION_CODEC = new GzipCompressionCodec();

    @Override
    public String newToken(Map<String, String> attributes) {

        // Create claims
        final Claims claims = Jwts
                .claims()
                .setExpiration(new Date(System.currentTimeMillis() + expiration));
                 claims.putAll(attributes);

        // Create a new token
        return Jwts
                .builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compressWith(COMPRESSION_CODEC)
                .compact();
    }
    
    @Override
    public Map<String, String> verify(String token) {
        
        Map<String, String>  attributes = new HashMap<>();
        try {
            
            // Decrypt claim
            attributes = Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody()
                    .entrySet().stream()
                    .collect(Collectors.toMap(claim -> claim.getKey(), claim -> String.valueOf(claim.getValue())));

        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } 
        return attributes;
    }

}
