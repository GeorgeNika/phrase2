package il.george_nika.phrase2.model.data.verb;

import il.george_nika.phrase2.model.data.LanguageUnit;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by George on 30.06.2017.
 */
@Data
@Entity
@Table(name = "verb_data")
public class VerbData {

    @Id
    @SequenceGenerator(name="verb_data_seq", sequenceName="verb_data_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="verb_data_seq")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "time")
    private Integer time;

    @Column(name = "person")
    private Integer person;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="language_unit_fk")
    private LanguageUnit languageUnit;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="verb_fk")
    private Verb verb;

    public void updateVerbDate(VerbData source){
        this.gender = source.getGender();
        this.quantity = source.getQuantity();
        this.time = source.getTime();
        this.person = source.getPerson();
        this.languageUnit.updateLanguageUnit(source.getLanguageUnit());
    }

    public VerbData(){}

    public VerbData(VerbData verbData, Boolean isNeedToSetVerb){
        this.id = verbData.getId();
        this.languageUnit = new LanguageUnit();
        if (isNeedToSetVerb) {
            this.verb = verbData.getVerb();
        }
        updateVerbDate(verbData);
    }
}
