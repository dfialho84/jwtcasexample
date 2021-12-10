package br.com.sekka.jwtcasexample.jwtsecurity;

import javax.servlet.http.HttpServletRequest;

public interface IpExtractor {
    String extractIp(HttpServletRequest request);
}
