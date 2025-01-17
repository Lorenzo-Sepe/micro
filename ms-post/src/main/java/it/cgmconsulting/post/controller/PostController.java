package it.cgmconsulting.post.controller;

import it.cgmconsulting.post.dto.PostRequestDto;
import it.cgmconsulting.post.dto.PostResponseDto;
import it.cgmconsulting.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/R2/")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostRequestDto request, @RequestHeader("username") String author) {
        return new ResponseEntity<PostResponseDto>(postService.createPost(request , author),HttpStatus.CREATED);
    }

    @PostMapping("/R2/editPost/{postId}")
    public ResponseEntity<PostResponseDto> editPost(@RequestBody @Valid PostRequestDto request, @RequestHeader("username") String author, @PathVariable("postId") int postId) {
        return new ResponseEntity<PostResponseDto>(postService.editPost(request , author, postId),HttpStatus.OK);
    }
}
