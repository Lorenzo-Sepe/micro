package it.cgmconsulting.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class CircuitBreakerService {

    @Value("${application.security.internalToken}")
    private String internalToken;

    public ResponseEntity<Void> massiveUpdate(String oldName, String newName){
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:9090/ms-post/RI/?oldName="+oldName+"&newName="+newName;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization-Internal", internalToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Void> response = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, Void.class);
        return response;
    }
}
