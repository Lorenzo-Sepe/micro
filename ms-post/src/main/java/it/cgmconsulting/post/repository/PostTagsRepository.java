package it.cgmconsulting.post.repository;

import it.cgmconsulting.post.entity.PostTags;
import it.cgmconsulting.post.entity.PostTagsId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PostTagsRepository extends JpaRepository<PostTags, PostTagsId> {

    // SELECT pt.tag FROM post_tags WHERE pt.post_id = ? ORDER BY pt.tag;
    @Query(value="SELECT pt.postTagsId.tag " +
            "FROM PostTags pt " +
            "WHERE pt.postTagsId.post.id = :postId " +
            "ORDER BY pt.postTagsId.tag")
    Set<String> getTagsByPost(int postId);

    @Modifying @Transactional
    @Query(value="DELETE FROM post_tags WHERE post_id = :postId", nativeQuery = true)
    void cleanTags(int postId);

    @Modifying @Transactional
    @Query(value="INSERT INTO post_tags VALUES(:tag, :postId)", nativeQuery = true)
    void addTag(String tag, int postId);

    @Modifying @Transactional
    @Query(value="DELETE FROM post_tags WHERE tag = :tag", nativeQuery = true)
    void deleteAssociationPostsTag(String tag);

}
