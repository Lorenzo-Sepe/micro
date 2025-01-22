package it.cgmconsulting.post.repository;

import it.cgmconsulting.post.entity.Section;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SectionRepository extends JpaRepository<Section, Integer> {

    boolean existsByPostIdAndPrg(int postId, byte prg);

    boolean existsByPostIdAndPrgAndIdIsNot(int postId, byte prg, int sectionId);

    @Modifying @Transactional
    @Query(value="DELETE FROM Section s WHERE s.id = :sectionId")
    void deleteSection(int sectionId);
}
