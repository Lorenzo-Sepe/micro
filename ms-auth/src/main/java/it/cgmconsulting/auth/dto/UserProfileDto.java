package it.cgmconsulting.auth.dto;

import it.cgmconsulting.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserProfileDto {

    private int id;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    public static UserProfileDto fromEntityToDto(User user){
        return UserProfileDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
