package br.com.sekka.jwtcasexample;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;


public class JWTFilter extends RequestHeaderAuthenticationFilter {
    
    public JWTFilter() {
        setExceptionIfHeaderMissing(false);
        setPrincipalRequestHeader("Authorization");
    }

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
