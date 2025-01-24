package it.cgmconsulting.post.repository;


import it.cgmconsulting.post.entity.PostTag;
import it.cgmconsulting.post.entity.PostTagsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostTagsRepository extends JpaRepository<PostTag, PostTagsId> {

   @Query(value="SELECT pt.postTagsId.tag " +
           "FROM PostTag pt " +
           "WHERE pt.postTagsId.post.id=:postId " +
           "ORDER BY pt.postTagsId.tag ")
   Set<String> getTagsByPostId(int postId);
}
