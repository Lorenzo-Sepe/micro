package it.cgmconsulting.tag.repository;

import it.cgmconsulting.tag.dto.TagDto;
import it.cgmconsulting.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, String> {

    @Query(value="SELECT new it.cgmconsulting.tag.dto.TagDto(" +
            "t.id, " +
            "t.visible" +
            ") FROM Tag t ORDER BY t.id")
    Set<TagDto> getAllTags();

    @Query(value="SELECT t.id " +
            "FROM Tag t WHERE t.visible=true ORDER BY t.id")
    Set<String> getVisibleTags();

    /* operazione più lenta
    @Modifying @Transactional
    @Query(value="INSERT INTO Tag t (t.id, t.visible, t.createdAt) VALUES (:tag, true, :now)")
    void createTag(String tag, LocalDateTime now);
    */

}
