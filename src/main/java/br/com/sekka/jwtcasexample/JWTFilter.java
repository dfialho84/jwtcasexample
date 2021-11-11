package br.com.sekka.jwtcasexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JWTFilter extends RequestHeaderAuthenticationFilter {
    
    public JWTFilter() {
        setExceptionIfHeaderMissing(false);
        setPrincipalRequestHeader("Authorization");
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
