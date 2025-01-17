package it.cgmconsulting.post.dto;

import it.cgmconsulting.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PostResponseDto {
    private int id;
    private String title;
    private String author;

    public static PostResponseDto fromEntityToDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .author(post.getAuthor())
                .build();
    }
}
