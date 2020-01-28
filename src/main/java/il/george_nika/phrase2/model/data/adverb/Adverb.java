package il.george_nika.phrase2.model.data.adverb;

import il.george_nika.phrase2.model.data.LanguageUnit;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "adverb")
public class Adverb {

    @Id
    @SequenceGenerator(name="adverb_seq", sequenceName="adverb_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="adverb_seq")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="main_form__fk")
    private LanguageUnit mainForm;

}
