package br.com.sekka.jwtcasexample.jwtsecurity;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class JWTAuthenticationProvider extends PreAuthenticatedAuthenticationProvider {
    
    private JWTTokenParser parser;
    
    public JWTAuthenticationProvider(JWTTokenParser parser) {
        super();
        this.parser = parser;        
    }

    private boolean validateClientIp(String requestIP, String tokenIp) {
        return requestIP == tokenIp;
        //return true;
    }

    private void log(String msg) {
        System.out.println(msg);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticatedUser = null;
        JWTPrincipal principal = (JWTPrincipal)authentication.getPrincipal();
        log(principal.getToken());
        JWTTokenDetails details = parser.parse(principal.getToken());
        
        
        if(details != null && validateClientIp(principal.getIp(), details.getIp())) {
            log("Details nao eh null");
            authenticatedUser = new PreAuthenticatedAuthenticationToken(details.getUsername(), "", details.getClaims());
            authenticatedUser.setAuthenticated(true);
            return authenticatedUser;
        } else {
            log("Lancando BadCreentials");
            throw new BadCredentialsException("Token Invalido");
            //return authentication;
        }        
    }
}
