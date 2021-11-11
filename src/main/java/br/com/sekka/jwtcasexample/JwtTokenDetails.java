package br.com.sekka.jwtcasexample;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class JwtTokenDetails implements Serializable {
    
    private String username;
    private String ip;

    private Date expirationDate;
}