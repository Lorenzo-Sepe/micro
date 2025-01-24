package it.cgmconsulting.post.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor
public class PostDetailResponse {

    private PostResponseDto post;
    private List<SectionResponseDto> sections;
    private Set<String> tags;

}
