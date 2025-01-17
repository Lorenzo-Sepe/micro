package it.cgmconsulting.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class SectionRequestDto {

    @NotBlank @Size(max = 100, min = 1)
    private String sectionTitle;
    @NotBlank @Size(max = 2000, min = 10)
    private String sectionContent;
    @Range(min = 1, max = Byte.MAX_VALUE)
    private byte prg;
}
