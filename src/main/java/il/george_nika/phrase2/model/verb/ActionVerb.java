package il.george_nika.phrase2.model.verb;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by George on 16.06.2017.
 */
@Data
@Entity
@Table(name = "active_verb")
public class ActionVerb {

    @Id
    @SequenceGenerator(name="active_verb_seq", sequenceName="active_verb_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="active_verb_seq")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name="verb_fk")
    private Verb verb;
}
