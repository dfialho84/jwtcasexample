package br.com.sekka.jwtcasexample.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class MyController {

    private String encodedKey = "N7CKwvogYX4qhxLslpFk4pJUSaxkIzAJA7N23BwQqavLViCpj1bJLuWZltYzB2la-pAgEYsr31LMvngSP_uKQw";
    private JwtUtil jwtUtil = new JwtUtil(encodedKey);
    private String token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsIm9yZy5hcGVyZW8uY2FzLnNlcnZpY2VzLlJlZ2lzdGVyZWRTZXJ2aWNlIjoiMzAxIn0.eyJjbGllbnRJcEFkZHJlc3MiOiIxNzIuMjguMC4xIiwic3ViIjoiZGZpYWxobzg0IiwiaXNGcm9tTmV3TG9naW4iOnRydWUsImF1dGhlbnRpY2F0aW9uRGF0ZSI6IjIwMjEtMTEtMTFUMDA6Mzg6NTguMjE3NjIyWiIsInN1Y2Nlc3NmdWxBdXRoZW50aWNhdGlvbkhhbmRsZXJzIjoiUXVlcnlEYXRhYmFzZUF1dGhlbnRpY2F0aW9uSGFuZGxlciIsImlzcyI6Imh0dHBzOi8vY2FzLmV4YW1wbGUub3JnOjg0NDMvY2FzIiwidXNlckFnZW50IjoiTW96aWxsYS81LjAgKFgxMTsgTGludXggeDg2XzY0KSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvOTUuMC40NjM4LjY5IFNhZmFyaS81MzcuMzYiLCJjcmVkZW50aWFsVHlwZSI6IlVzZXJuYW1lUGFzc3dvcmRDcmVkZW50aWFsIiwiYXVkIjoiaHR0cDovL3NlcnZpY28xLmV4YW1wbGUub3JnIiwiYXV0aGVudGljYXRpb25NZXRob2QiOiJRdWVyeURhdGFiYXNlQXV0aGVudGljYXRpb25IYW5kbGVyIiwic2VydmVySXBBZGRyZXNzIjoiMTcyLjI4LjAuMyIsImxvbmdUZXJtQXV0aGVudGljYXRpb25SZXF1ZXN0VG9rZW5Vc2VkIjpmYWxzZSwiZXhwIjoxNjM2NjE5OTM4LCJpYXQiOjE2MzY1OTExMzgsImp0aSI6IlNULTEtdjc2U2pwLXl3c0NEOGxUZGU2M0ktYnZkMk9ZLTQ5ZTgxODU3N2RiOCJ9.NUsU-vmr7L0fuZKr5e2x-Kn8sNt1LDiIHgUVUCig-Gaa_ulbKCwh47fJ7HRZQgPjlxl5RkDuYcTBD5OQ-E-mKg";
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


    @GetMapping("/public")
    public String publicEndpoint() {
        //String exp = jwtUtil.parse(token);
        
        //log.info(exp);
        return "Public endpoint Response";
    }

    @GetMapping("/protected")
    public String protectedEndpoint() {
        return "Protected endpoint Response";
    }
}
