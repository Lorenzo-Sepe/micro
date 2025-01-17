package it.cgmconsulting.post.service;

import it.cgmconsulting.post.dto.PostRequestDto;
import it.cgmconsulting.post.dto.PostResponseDto;
import it.cgmconsulting.post.entity.Post;
import it.cgmconsulting.post.exception.ResourceNotFoundException;
import it.cgmconsulting.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto request, String author){
        Post post = new Post(request.getTitle(), LocalDateTime.now(), author);
        postRepository.save(post);
        return PostResponseDto.fromEntityToDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(PostRequestDto request, String author, int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        post.setTitle(request.getTitle());
        post.setAuthor(author);
        post.setUpdatedAt(LocalDateTime.now());
        post.setPublicationDate(null);
        return PostResponseDto.fromEntityToDto(post);

    }

}
