package it.cgmconsulting.post.repository;

import it.cgmconsulting.post.dto.SectionResponseDto;
import it.cgmconsulting.post.entity.Section;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Integer> {

    boolean existsByPostIdAndPrg(int postId, byte prg);

    boolean existsByPostIdAndPrgAndIdIsNot(int postId, byte prg, int sectionId);

    @Modifying @Transactional
    @Query(value="DELETE FROM Section s WHERE s.id = :sectionId")
    void deleteSection(int sectionId);

    @Query(value="SELECT new it.cgmconsulting.post.dto.SectionResponseDto(" +
            "s.id, " +
            "s.sectionTitle, " +
            "s.sectionContent, " +
            "s.prg" +
            ") FROM Section s " +
            "WHERE s.post.id = :postId " +
            "ORDER BY s.prg")
    List<SectionResponseDto> getSectionsByPost(int postId);
}
