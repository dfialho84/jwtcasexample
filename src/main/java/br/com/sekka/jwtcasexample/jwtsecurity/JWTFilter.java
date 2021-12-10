package br.com.sekka.jwtcasexample.jwtsecurity;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class JWTFilter extends AbstractPreAuthenticatedProcessingFilter {

    private IpExtractor ipExtractor;

    private JWTFilter() {
        super();
    } 

    public JWTFilter(IpExtractor ipExtractor) {
        super();
        this.ipExtractor = ipExtractor;
    }

    private String extractToken(HttpServletRequest request) {
        String rawToken = request.getHeader("Authorization");
        if(rawToken != null) {
            return request.getHeader("Authorization").substring(7);
        }
        return null;        
    }

    @Override    
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String token = extractToken(request);
        JWTPrincipal principal = null;
        if(token != null) {
            String ip = ipExtractor.extractIp(request);
            principal = new JWTPrincipal(token, ip);
        }        
        return principal;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return extractToken(request);
    }
}
