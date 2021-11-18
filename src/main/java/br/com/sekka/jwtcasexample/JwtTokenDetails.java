package br.com.sekka.jwtcasexample;

import java.io.Serializable;
import java.util.Date;

public class JwtTokenDetails implements Serializable {
    
    private String username;
    private String ip;

    private Date expirationDate;

    public JwtTokenDetails() {        
    }

    public JwtTokenDetails(String username, String ip, Date expirationDate) {
        this.username = username;
        this.ip = ip;
        this.expirationDate = expirationDate;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}