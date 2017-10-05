package il.george_nika.phrase2.model.view;

import il.george_nika.phrase2.model.LanguageUnit;
import lombok.Data;

/**
 * Created by George on 11.06.2017.
 */
@Data
public class ViewPhrase {

    private String russian = "";

    private String hebrew = "";

    private String transcription = "";

    public void addLanguageUnit(LanguageUnit languageUnit){
        this.russian = russian + " " + languageUnit.getRussian();
        this.hebrew = hebrew + " " + languageUnit.getHebrew();
        this.transcription = transcription + " " + languageUnit.getTranscription();
    }

}
