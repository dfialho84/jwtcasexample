package br.com.sekka.jwtcasexample;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtToken extends AbstractAuthenticationToken {
    
    private final static long serialVersionUID = -1L;
    
    private UserDetails principal;
    private String jwtToken;

    public JwtToken(UserDetails principal, String jwtToken) {
        super(principal.getAuthorities());
        this.principal = principal;
        this.jwtToken = jwtToken;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {        
        return principal;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
