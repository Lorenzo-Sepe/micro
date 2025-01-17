package it.cgmconsulting.post.service;

import it.cgmconsulting.post.dto.PostRequestDto;
import it.cgmconsulting.post.dto.PostResponseDto;
import it.cgmconsulting.post.entity.Post;
import it.cgmconsulting.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service @RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto request, String author) {
        Post post = new Post(request.getPostTitle(), author, LocalDateTime.now());
        postRepository.save(post);


        return PostResponseDto.fromEntityToDto(post);
    }
}
