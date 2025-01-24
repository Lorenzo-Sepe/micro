package it.cgmconsulting.post.service;

import it.cgmconsulting.post.dto.PostDetailResponse;
import it.cgmconsulting.post.dto.PostRequestDto;
import it.cgmconsulting.post.dto.PostResponseDto;
import it.cgmconsulting.post.dto.SectionResponseDto;
import it.cgmconsulting.post.entity.Post;
import it.cgmconsulting.post.entity.PostTags;
import it.cgmconsulting.post.entity.PostTagsId;
import it.cgmconsulting.post.exception.ResourceNotFoundException;
import it.cgmconsulting.post.repository.PostRepository;
import it.cgmconsulting.post.repository.PostTagsRepository;
import it.cgmconsulting.post.repository.SectionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final SectionRepository sectionRepository;
    private final PostTagsRepository postTagsRepository;

    public PostResponseDto createPost(PostRequestDto request, String author){
        Post post = new Post(request.getTitle(), LocalDateTime.now(), author);
        postRepository.save(post);
        return PostResponseDto.fromEntityToDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(PostRequestDto request, String author, int postId) {
        Post post = findPost(postId);
        post.setTitle(request.getTitle());
        post.setAuthor(author);
        post.setUpdatedAt(LocalDateTime.now());
        post.setPublicationDate(null);
        return PostResponseDto.fromEntityToDto(post);

    }

    protected Post findPost(int id){
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }


    public PostDetailResponse getPostDetail(int postId) {
        // recupero il dto relativo al post
        PostResponseDto postResponseDto = postRepository.getPostDetail(postId, LocalDate.now())
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        // recupero le sezioni legate al post in oggetto
        List<SectionResponseDto> sections = sectionRepository.getSectionsByPost(postId);
        // recupero i tag associati al post
        Set<String> tags = postTagsRepository.getTagsByPost(postId);
        // compongo e restituisco il dettaglio del post
        return new PostDetailResponse(postResponseDto, sections, tags);
    }

    public List<PostResponseDto> getPosts(int pageNumber, int pageSize, String sortBy, String direction) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.valueOf(direction.toUpperCase()), sortBy);
        Page<PostResponseDto> list = postRepository.getPosts(LocalDate.now(), pageable);
        return list.getContent();
    }

    public void updateAuthorUsername(String oldName, String newName) {
        postRepository.updateAuthorUsername(oldName, newName);
    }

    public Set<String> addTagsToPost(int postId, Set<String> tags) {
        // elimino tutti i tag precedentemente aasociati al post
        postTagsRepository.cleanTags(postId);
        for(String t : tags)
            postTagsRepository.addTag(t.toUpperCase(), postId);
        return tags;
    }

    public void deleteAssociationPostsTag(String tag) {
        postTagsRepository.deleteAssociationPostsTag(tag);
    }
}
