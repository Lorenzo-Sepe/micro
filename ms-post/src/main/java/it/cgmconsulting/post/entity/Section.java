package it.cgmconsulting.post.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false,length = 100)
    private String sectionTitle;

    @Column(nullable = false,length = 2000)
    private String sectionContent;

    private byte prg; //progessivo per odinare

    @ManyToOne(fetch= FetchType.LAZY)
    private Post post;
}
