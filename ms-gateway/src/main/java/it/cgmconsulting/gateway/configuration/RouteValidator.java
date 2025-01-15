package it.cgmconsulting.gateway.configuration;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

    // R0  -> no token
    // R1  -> ADMIN
    // R2  -> WRITER
    // R3  -> MEMBER
    // R4  -> MODERATOR


    public boolean isOpenEndpoint(ServerHttpRequest req){
        return (req.getURI().getPath().contains("/R0/"));
    }
}
