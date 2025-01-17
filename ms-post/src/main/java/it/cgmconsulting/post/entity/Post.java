package it.cgmconsulting.post.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false,length = 200)
    private String title;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Section> sections;

    @Column(nullable = false,updatable=false)
    private LocalDateTime createdAt;
    private LocalDateTime publicationDate;
    private LocalDateTime updateAt;

    @Column(nullable = false,length = 200)
    private String author;
    
    public Post(String title, String author, LocalDateTime createdAt) {
        this.title = title;
        this.createdAt = createdAt;
        this.author = author;
    }

}
