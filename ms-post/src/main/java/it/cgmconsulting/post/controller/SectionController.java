package it.cgmconsulting.post.controller;

import it.cgmconsulting.post.dto.SectionRequestDto;
import it.cgmconsulting.post.dto.SectionResponseDto;
import it.cgmconsulting.post.service.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping("/R2/section/{postId}")
    public ResponseEntity<SectionResponseDto> createSection(@RequestBody @Valid SectionRequestDto request, @PathVariable int postId){
        return new ResponseEntity<SectionResponseDto>(sectionService.createSection(request, postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/R2/section/{sectionId}")
    public ResponseEntity<Void> deleteSection(@PathVariable int sectionId){

        return new ResponseEntity<Void>(sectionService.deleteSection(sectionId), HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/R2/section/{sectionId}")
    public ResponseEntity<SectionResponseDto> updateSection(@RequestBody @Valid SectionRequestDto request, @PathVariable int sectionId){
        return new ResponseEntity<SectionResponseDto>(sectionService.updateSection(request, sectionId), HttpStatus.OK);
    }

}
