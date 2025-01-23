package it.cgmconsulting.post.repository;

import it.cgmconsulting.post.dto.PostResponseDto;
import it.cgmconsulting.post.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value="SELECT new it.cgmconsulting.post.dto.PostResponseDto(" +
            "p.id, " +
            "p.title, " +
            "p.author, " +
            "p.publicationDate" +
            ") FROM Post p " +
            "WHERE p.id = :postId " +
            "AND p.publicationDate IS NOT NULL " +
            "AND p.publicationDate <= :now")
    Optional<PostResponseDto> getPostDetail(int postId, LocalDate now);

    @Query(value="SELECT new it.cgmconsulting.post.dto.PostResponseDto(" +
            "p.id, " +
            "p.title, " +
            "p.author, " +
            "p.publicationDate" +
            ") FROM Post p " +
            "WHERE p.publicationDate IS NOT NULL " +
            "AND p.publicationDate <= :now")
    Page<PostResponseDto> getPosts(LocalDate now, Pageable pageable);


    @Modifying @Transactional
    @Query(value="UPDATE Post p SET p.author = :newName WHERE p.author = :oldName")
    void updateAuthorUsername(String oldName, String newName);
}
