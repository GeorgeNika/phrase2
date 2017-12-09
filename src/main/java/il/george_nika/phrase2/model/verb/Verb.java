package il.george_nika.phrase2.model.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "verb")
public class Verb {

    @Id
    @SequenceGenerator(name="verb_seq", sequenceName="verb_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="verb_seq")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="infinitive_fk")
    private LanguageUnit infinitive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "verb", cascade = CascadeType.ALL)
    private List<VerbData> verbDataCollection;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="preposition_fk")
    private LanguageUnit preposition;

}