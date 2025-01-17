package it.cgmconsulting.post.dto;

import it.cgmconsulting.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PostResponseDto {

    private int id;
    private String title;
    private String author;

    public static PostResponseDto fromEntityToDto(Post post){
        return new PostResponseDto(post.getId(), post.getTitle(), post.getAuthor());
    }

}
