package br.com.sekka.jwtcasexample.jwtsecurity;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class DefaultJWTTokenParser implements JWTTokenParser {

    private SignatureAlgorithm signatureAlgorithm;
    private Key secretKey;

    private void log(String msg) {
        System.out.println(msg);
    }

    public DefaultJWTTokenParser(String key) {
        log(key);
        signatureAlgorithm = SignatureAlgorithm.HS512;         
        decodeKey(key);        
    }

    private void decodeKey(String key) {        
        secretKey = new SecretKeySpec(key.getBytes(), signatureAlgorithm.getJcaName());
    }

    @Override
    public JWTTokenDetails parse(String token) {
        JWTTokenDetails details = null;

        log("Antes do try");
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            String ip = claims.get("clientIpAddress", String.class);
            log("Passo 1");

            List<GrantedAuthority> authorities = new ArrayList<>();
            
            log("Passo 2");
            details = new JWTTokenDetails(username, ip, expirationDate, authorities);            
            log("Passo 3");
        } catch(JwtException ex) {
            ex.printStackTrace();
            log("JWTEXCETION ---------------");
        }
        
        return details;
    }
    
}
