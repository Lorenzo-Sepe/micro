package it.cgmconsulting.tag.controller;

import it.cgmconsulting.tag.dto.TagDto;
import it.cgmconsulting.tag.service.TagService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@Validated
public class TagController {

    private final TagService tagService;

    @PostMapping("/R1/") // localhost:9090/ms-tag/?tag=xxxxxx
    public ResponseEntity<String> createTag(@RequestParam @NotBlank @Size(max=30, min=3) String tag){
        return new ResponseEntity<String>(tagService.createTag(tag), HttpStatus.CREATED);
    }

    @PatchMapping("/R1/")
    public ResponseEntity<TagDto> switchTagVisibility(@RequestParam @NotBlank @Size(max=30, min=3) String tag){
        return ResponseEntity.ok(tagService.switchTagVisibility(tag));
    }

    @GetMapping("/R1/")
    public ResponseEntity<Set<TagDto>> getTags(){
        return ResponseEntity.ok(tagService.getTags());
    }

    @GetMapping("/R0/tags")
    public ResponseEntity<Set<String>> getVisibleTags(){
        return ResponseEntity.ok(tagService.getVisibleTags());
    }
}
