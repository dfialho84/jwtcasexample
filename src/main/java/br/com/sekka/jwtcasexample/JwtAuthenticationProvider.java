package br.com.sekka.jwtcasexample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import br.com.sekka.jwtcasexample.controllers.*;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private JwtUtil jwtUtil;

    public JwtAuthenticationProvider() {
        super();
        String secretKey = "N7CKwvogYX4qhxLslpFk4pJUSaxkIzAJA7N23BwQqavLViCpj1bJLuWZltYzB2la-pAgEYsr31LMvngSP_uKQw";
        jwtUtil = new JwtUtil(secretKey);
    }

    private boolean isPreauthenticatedToken(Authentication authentication) {
        return authentication.getClass().isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
            && authentication.getPrincipal() != null;
    }

    private UserDetails parseToken(String tokenHeader) {
        UserDetails principal = null;
        JwtTokenDetails details = jwtUtil.parse(tokenHeader);
        if(details != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            principal = new User(details.getUsername, "", authorities);
        }
        return principal;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticatedUser = null;

        if(isPreauthenticatedToken(authentication)) {
            String tokenHeader = (String) authentication.getPrincipal();
            UserDetails userDetails = parseToken(tokenHeader);
            if(userDetails != null) {
                authenticatedUser = new JwtToken(userDetails, tokenHeader);                
            }             
        } else {
            authenticatedUser = authentication;
        }
        
        return authenticatedUser;        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
            || authentication.isAssignableFrom(JwtToken.class);
    }
    
}
