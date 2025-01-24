package it.cgmconsulting.auth.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class CircuitBreakerService {

    @Value("${application.security.internalToken}")
    private String internalToken;

    @CircuitBreaker(name= "a-tentativi", fallbackMethod = "fallbackMethodmassiveUpdate")
    public ResponseEntity<Void> massiveUpdate(String oldName, String newName){
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:9090/ms-post/RI/?oldName="+oldName+"&newName="+newName;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization-Internal", internalToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Void> response = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, Void.class);
        return response;
    }

    public ResponseEntity<Void> fallbackMethodmassiveUpdate(String oldName, String newName, Throwable e){
        log.error("Impossibile modificare username, cause: "+e.getMessage());
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
