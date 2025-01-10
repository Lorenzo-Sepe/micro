package it.cgmconsulting.tag.controller;

import it.cgmconsulting.tag.dto.TagDto;
import it.cgmconsulting.tag.service.TagService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<String> createTag(@RequestParam @NotBlank @Size(max=30, min=3) String tag){
        return new ResponseEntity<String>(tagService.createTag(tag), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<TagDto> switchTagVisibility(@RequestParam @NotBlank @Size(max=30, min=3) String tag){
        return ResponseEntity.ok(tagService.switchTagVisibility(tag));
    }

    @GetMapping
    public ResponseEntity<Set<TagDto>> getTags(){
        return ResponseEntity.ok(tagService.getTags());
    }

    @GetMapping("/tags")
    public ResponseEntity<Set<String>> getVisibleTags(){
        return ResponseEntity.ok(tagService.getVisibleTags());
    }
}
