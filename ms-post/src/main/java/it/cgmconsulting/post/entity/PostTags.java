package it.cgmconsulting.post.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class PostTags {

    @EmbeddedId
    private PostTagsId postTagsId;
}
