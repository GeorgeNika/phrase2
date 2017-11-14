package il.george_nika.phrase2.model.adjective;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.noun.NounData;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "adjective")
public class Adjective {

    @Id
    @SequenceGenerator(name="adjective_seq", sequenceName="adjective_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="adjective_seq")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="main_form__fk")
    private LanguageUnit mainForm;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "adjective", cascade = CascadeType.ALL)
    private List<AdjectiveData> adjectiveDataCollection;
}
