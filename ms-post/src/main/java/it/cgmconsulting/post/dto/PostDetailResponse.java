package it.cgmconsulting.post.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class PostDetailResponse {

    private PostResponseDto post;
    private List<SectionResponseDto> sections;

}
