package it.cgmconsulting.post.controller;

import it.cgmconsulting.post.dto.SectionRequestDto;
import it.cgmconsulting.post.dto.SectionResponseDto;
import it.cgmconsulting.post.service.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class SectionController {
    private final SectionService sectionService;


}
