package il.george_nika.phrase2.model.noun;

import il.george_nika.phrase2.model.LanguageUnit;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "noun")
public class Noun {

    @Id
    @SequenceGenerator(name="noun_seq", sequenceName="noun_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="noun_seq")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="main_form__fk")
    private LanguageUnit mainForm;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "noun", cascade = CascadeType.ALL)
    private List<NounData> nounDataCollection;
}
