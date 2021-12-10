package br.com.sekka.jwtcasexample.jwtsecurity;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class JWTTokenDetails {
    
    private String username;
    private String ip;
    private Date expirationDate;
    private List<GrantedAuthority> claims;

    public JWTTokenDetails(String username, String ip, Date expirationDate, List<GrantedAuthority> claims) {
        this.username = username;
        this.ip = ip;
        this.expirationDate = expirationDate;
        this.claims = claims;
    }

    public List<GrantedAuthority> getClaims() {
        return claims;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getUsername() {
        return username;
    }

    public String getIp() {
        return ip;
    }
}
