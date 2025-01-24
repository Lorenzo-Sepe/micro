package it.cgmconsulting.tag.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class CircuitBreakerService {

    @Value("${application.security.internalToken}")
    private String internalToken;


    @CircuitBreaker(name = "delete-association-posts-tag", fallbackMethod = "fallbackMethodDeleteAssociationPostsTag")
    public ResponseEntity<Void> deleteAssociationPostsTag(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:9090/ms-post/RI/?tag="+id.toUpperCase();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization-Internal", internalToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Void> response = restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, Void.class);
        return response;
    }

    public ResponseEntity<Void> fallbackMethodDeleteAssociationPostsTag(String id, Throwable e){
        log.error("MS-POST not available: "+e.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
