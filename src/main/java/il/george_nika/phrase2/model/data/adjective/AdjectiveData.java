package il.george_nika.phrase2.model.data.adjective;

import il.george_nika.phrase2.model.data.LanguageUnit;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "adjective_data")
public class AdjectiveData {

    @Id
    @SequenceGenerator(name="adjective_data_seq", sequenceName="adjective_data_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="adjective_data_seq")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="language_unit_fk")
    private LanguageUnit languageUnit;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="adjective_fk")
    private Adjective adjective;

    public void updateAdjectiveDate(AdjectiveData source){
        this.gender = source.getGender();
        this.quantity = source.getQuantity();
        this.languageUnit.updateLanguageUnit(source.getLanguageUnit());
    }

    public AdjectiveData(){}

    public AdjectiveData(AdjectiveData adjectiveData, Boolean isNeedToSetAdjective){
        this.id = adjectiveData.getId();
        this.languageUnit = new LanguageUnit();
        if (isNeedToSetAdjective) {
            this.adjective = adjectiveData.getAdjective();
        }
        updateAdjectiveDate(adjectiveData);
    }

}
