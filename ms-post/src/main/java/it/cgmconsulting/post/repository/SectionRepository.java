package it.cgmconsulting.post.repository;

import it.cgmconsulting.post.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Integer> {
}
