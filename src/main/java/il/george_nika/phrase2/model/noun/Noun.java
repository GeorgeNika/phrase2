package il.george_nika.phrase2.model.noun;

import il.george_nika.phrase2.model.LanguageUnit;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "noun")
public class Noun {

    @Id
    @SequenceGenerator(name="noun_seq", sequenceName="noun_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="noun_seq")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="language_unit_fk")
    private LanguageUnit languageUnit;
}
