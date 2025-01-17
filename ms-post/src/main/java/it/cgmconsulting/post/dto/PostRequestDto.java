package it.cgmconsulting.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostRequestDto {

    @NotBlank @Size(max = 200, min = 2)
    private String title;

}
