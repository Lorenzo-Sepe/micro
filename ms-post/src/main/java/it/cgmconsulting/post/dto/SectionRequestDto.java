package it.cgmconsulting.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Data @AllArgsConstructor
public class SectionRequestDto {
    private String sectionTitle;
    private String sectionContent;
    private byte prg;

}

/*
* @Data @NoArgsConstructor @AllArgsConstructor @Builder
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
}*/