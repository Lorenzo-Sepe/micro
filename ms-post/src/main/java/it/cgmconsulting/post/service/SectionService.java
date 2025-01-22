package it.cgmconsulting.post.service;

import it.cgmconsulting.post.dto.SectionRequestDto;
import it.cgmconsulting.post.dto.SectionResponseDto;
import it.cgmconsulting.post.entity.Post;
import it.cgmconsulting.post.entity.Section;
import it.cgmconsulting.post.exception.ConflictException;
import it.cgmconsulting.post.exception.ResourceNotFoundException;
import it.cgmconsulting.post.repository.SectionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final PostService postService;

    public SectionResponseDto createSection(SectionRequestDto request, int postId) {
        // Verificare che il post a cui si intende associare la sezione esista
        Post post = postService.findPost(postId);
        // Verificare che non esista già un'altra sezione relativa al post con lo stesso progressivo
        byte prg = request.getPrg();
        if(sectionRepository.existsByPostIdAndPrg(postId, prg))
            throw new ConflictException("Progressive already in use for the post "+postId);
        Section section = Section.builder()
                .sectionTitle(request.getSectionTitle())
                .sectionContent(request.getSectionContent())
                .createdAt(LocalDateTime.now())
                .prg(prg)
                .post(post)
                .build();
        sectionRepository.save(section);
        return SectionResponseDto.fromEntityToDto(section);

    }

    @Transactional
    public Void deleteSection(int sectionId) {
        sectionRepository.deleteSection(sectionId);
        return null;
    }


    @Transactional
    public SectionResponseDto updateSection(SectionRequestDto request, int sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(()-> new ResourceNotFoundException("Section", "id", sectionId));

        byte prg = request.getPrg();
        if(sectionRepository.existsByPostIdAndPrgAndIdIsNot(section.getPost().getId(), prg, sectionId))
            throw new ConflictException("Progressive already in use for the post");
        LocalDateTime now = LocalDateTime.now();
        // aggiorno la sezione
        section.setSectionTitle(request.getSectionTitle());
        section.setSectionContent(request.getSectionContent());
        section.setUpdatedAt(now);
        section.setPrg(prg);
        // spubblico il post (dal momento che il suo contenuto/section è stato modificato)
        section.getPost().setPublicationDate(null);
        section.getPost().setUpdatedAt(now);
        return SectionResponseDto.fromEntityToDto(section);
    }
}
