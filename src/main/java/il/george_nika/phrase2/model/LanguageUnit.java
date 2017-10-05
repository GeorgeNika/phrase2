package il.george_nika.phrase2.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by George on 16.06.2017.
 */
@Data
@Entity
@Table(name = "language_unit")
public class LanguageUnit implements Serializable {

    @Id
    @SequenceGenerator(name="language_unit_seq", sequenceName="language_unit_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="language_unit_seq")
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Integer id;

    @Column(name = "russian", length = 20)
    private String russian;

    @Column(name = "hebrew", length = 15)
    private String hebrew;

    @Column(name = "transcription", length = 20)
    private String transcription;

    public LanguageUnit() {
    }

    public LanguageUnit(String russian, String hebrew, String transcription) {
        this.russian = russian;
        this.hebrew = hebrew;
        this.transcription = transcription;
    }

    public void updateLanguageUnit(LanguageUnit source){
        this.russian = source.getRussian();
        this.hebrew = source.getHebrew();
        this.transcription = source.getTranscription();
    }

    public void concateLanguageUnit(LanguageUnit source){
        this.russian = this.russian + source.getRussian();
        this.hebrew = this.hebrew + source.getHebrew();
        this.transcription = this.transcription + source.getTranscription();
    }
}
