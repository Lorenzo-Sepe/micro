package it.cgmconsulting.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class JwtAuthenticationDto {

    private Integer id;
    private String username;
    private String email;
    private String role;
    private String token;
}
