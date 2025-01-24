package it.cgmconsulting.post.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class PostTagsId {

    private String tag;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;
}
