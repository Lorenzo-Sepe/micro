package it.cgmconsulting.post.controller;

import it.cgmconsulting.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestTemplateController {

    private final PostService postService;

    // update massivo dello username dell'author del post
    // Questo endpoint viene richiamato da ms-auth quando un writer decide di modificare il proprio username
    @PutMapping("/RI/")
    public ResponseEntity<Void> updateAuthorUsername(@RequestParam String oldName, @RequestParam String newName){
        try {
            postService.updateAuthorUsername(oldName, newName);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            log.error("Massive username update failed: "+e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
