package it.cgmconsulting.post.dto;

import it.cgmconsulting.post.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SectionResponseDto {

    private int id;
    private String sectionTitle;
    private String sectionContent;
    private byte prg;

    public static SectionResponseDto fromEntityToDto(Section section){
        return new SectionResponseDto(
                section.getId(),
                section.getSectionTitle(),
                section.getSectionContent(),
                section.getPrg()
        );
    }
}
