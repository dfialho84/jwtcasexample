package br.com.sekka.jwtcasexample.jwtsecurity;

public class JWTPrincipal {
    
    private String token;
    private String ip;

    public JWTPrincipal(String token, String ip) {
        this.token = token;
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public String getToken() {
        return token;
    }
}
