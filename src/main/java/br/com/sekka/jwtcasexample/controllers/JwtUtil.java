package br.com.sekka.jwtcasexample.controllers;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;

import br.com.sekka.jwtcasexample.JwtTokenDetails;
import br.com.sekka.jwtcasexample.jwtsecurity.JWTTokenParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultSignatureValidatorFactory;
import io.jsonwebtoken.impl.crypto.SignatureValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil {
    
    private SignatureAlgorithm signatureAlgorithm;

    private Key secretKey;

    public JwtUtil(String key) {
        signatureAlgorithm = SignatureAlgorithm.HS512;         
        decodeKey(key);        
    }

    private void decodeKey(String key) {        
        secretKey = new SecretKeySpec(key.getBytes(), signatureAlgorithm.getJcaName());
    }

    public JwtTokenDetails parse(String token) {
        JwtTokenDetails details = null;

        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            String ip = claims.get("clientIpAddress", String.class);

            details = new JwtTokenDetails();
            details.setUsername(username);
            details.setExpirationDate(expirationDate);
            details.setIp(ip);
        } catch(JwtException ex) {
            log.error(ex.getMessage(), ex);
        }
        
        return details;
    }
    
}
