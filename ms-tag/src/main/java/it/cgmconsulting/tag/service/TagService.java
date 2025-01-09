package it.cgmconsulting.tag.service;

import it.cgmconsulting.tag.dto.TagDto;
import it.cgmconsulting.tag.entity.Tag;
import it.cgmconsulting.tag.exception.ConflictException;
import it.cgmconsulting.tag.exception.ResourceNotFoundException;
import it.cgmconsulting.tag.repository.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public String createTag(String newTag){
        if(tagRepository.existsById(newTag))
            throw new ConflictException("Tag already present");
        Tag tag = new Tag(newTag.toUpperCase());
        tagRepository.save(tag);
        return tag.getId();
    }

    @Transactional
    @CacheEvict(value={"all-tags", "visible-tags"}, allEntries = true)
    public TagDto switchTagVisibility(String id){
        Tag tag = tagRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tag", "id", id));
        tag.setVisible(!tag.isVisible());
        tag.setUpdatedAt(LocalDateTime.now());
        return new TagDto(id, tag.isVisible());
    }

    @Cacheable("all-tags")
    public Set<TagDto> getTags(){
        return tagRepository.getAllTags();
    }

    @Cacheable("visible-tags")
    public Set<TagDto> getVisibleTags(){
        return tagRepository.getVisibleTags();
    }



    /*
    public String createTag2(String newTag) throws SQLDuplicateException {
        newTag = newTag.toUpperCase();
        tagRepository.createTag(newTag, LocalDateTime.now());
        return newTag;
    }
    */

}
