package il.george_nika.phrase2.model.data.noun;

import il.george_nika.phrase2.model.data.LanguageUnit;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "noun_data")
public class NounData {

    @Id
    @SequenceGenerator(name="noun_data_seq", sequenceName="noun_data_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="noun_data_seq")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "gender_ru")
    private Integer genderRU;

    @Column(name = "quantity_ru")
    private Integer quantityRU;

    @Column(name = "gender_il")
    private Integer genderIL;

    @Column(name = "quantity_il")
    private Integer quantityIL;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="language_unit_fk")
    private LanguageUnit languageUnit;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="noun_fk")
    private Noun noun;

    public void updateNounDate(NounData source){
        this.genderRU = source.getGenderRU();
        this.quantityRU = source.getQuantityRU();
        this.genderIL = source.getGenderIL();
        this.quantityIL = source.getQuantityIL();
        this.languageUnit.updateLanguageUnit(source.getLanguageUnit());
    }

    public NounData(){}

    public NounData(NounData nounData, Boolean isNeedToSetNoun){
        this.id = nounData.getId();
        this.languageUnit = new LanguageUnit();
        if (isNeedToSetNoun) {
            this.noun = nounData.getNoun();
        }
        updateNounDate(nounData);
    }

}
