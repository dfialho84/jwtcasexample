package br.com.sekka.jwtcasexample.jwtsecurity;

public interface JWTTokenParser {
    JWTTokenDetails parse(String token);
}
