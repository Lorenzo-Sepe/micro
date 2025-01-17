package it.cgmconsulting.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@RequiredArgsConstructor @Getter
@AllArgsConstructor @Builder
public class PostRequestDto {

    @NotBlank
    @Size(min = 2, max = 200)
    private String postTitle;


}

