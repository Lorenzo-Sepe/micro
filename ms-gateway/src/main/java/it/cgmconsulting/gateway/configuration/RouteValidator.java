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
    // R99 -> SAURON (alias super admin)
    // RA  -> ALL (qualunque utente registrato a patto che il token sia valido)
    // RI  -> Request Interne (chiamate interne all'applicazione tra microservizi)


    public boolean isOpenEndpoint(ServerHttpRequest req){
        return (req.getURI().getPath().contains("/R0/"));
    }
}
