package it.cgmconsulting.post.controller;

import it.cgmconsulting.post.dto.PostDetailResponse;
import it.cgmconsulting.post.dto.PostRequestDto;
import it.cgmconsulting.post.dto.PostResponseDto;
import it.cgmconsulting.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/R2/")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostRequestDto request, @RequestHeader("username") String author){
        return new ResponseEntity<>(postService.createPost(request, author), HttpStatus.CREATED);
    }

    @PatchMapping("/R2/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@RequestBody @Valid PostRequestDto request, @RequestHeader("username") String author, @PathVariable("postId") int postId) {
        return new ResponseEntity<PostResponseDto>(postService.updatePost(request , author, postId),HttpStatus.OK);
    }

    @GetMapping("/R0/{postId}")
    public ResponseEntity<PostDetailResponse> getPostDetail(@PathVariable int postId){
        return ResponseEntity.ok(postService.getPostDetail(postId));
    }

    // restituisce gli ultimi n post pubblicati
    // ordinati per data di pubblicazione dal più recente al più vecchio
    @GetMapping("/R0/")
    public ResponseEntity<List<PostResponseDto>> getPosts(@RequestParam(defaultValue = "0") int pageNumber, // numero di pagina da cui partire; 0 è la prima pagina
                                                          @RequestParam(defaultValue = "5") int pageSize, // numero di elementi per pagina
                                                          @RequestParam(defaultValue = "publicationDate") String sortBy, // la colonna presa in considerazione per l'ordinamento
                                                          @RequestParam(defaultValue = "DESC") String direction // ASC o DESC, ordinamento ascendente o discendente
    ){
        return ResponseEntity.ok(postService.getPosts(pageNumber, pageSize, sortBy, direction));
    }

}
