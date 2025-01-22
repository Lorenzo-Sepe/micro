package it.cgmconsulting.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class PostDetailResponse {

    private int id; // id del post
    private String title;
    private LocalDate publicationDate;
    private String author;
    List<SectionResponseDto> sections;

}
