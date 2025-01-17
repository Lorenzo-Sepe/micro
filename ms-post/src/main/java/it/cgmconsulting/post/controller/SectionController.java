package it.cgmconsulting.post.controller;

import it.cgmconsulting.post.dto.SectionRequestDto;
import it.cgmconsulting.post.dto.SectionResponseDto;
import it.cgmconsulting.post.service.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping("/R2/section/{postId}")
    public ResponseEntity<SectionResponseDto> createSection(@RequestBody @Valid SectionRequestDto request, @PathVariable int postId){
        return null;
        //return new ResponseEntity<SectionResponseDto>(sectionService.createSection(request, postId), HttpStatus.CREATED);
    }
}
