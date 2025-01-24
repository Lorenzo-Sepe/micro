package it.cgmconsulting.post.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PostTagsId {

    private String postTags;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;
}
