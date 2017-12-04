package il.george_nika.phrase2.model.view;

import il.george_nika.phrase2.model.LanguageUnit;
import lombok.Data;

import java.util.List;

@Data
public class ViewPhrase {

    private String russian = "";

    private String hebrew = "";

    private String voice = "";

    private List<WordIdentification> wordsIdentification;

    public void addLanguageUnit(LanguageUnit languageUnit){
        this.russian = russian + " " + languageUnit.getRussian();
        this.hebrew = hebrew + " " + languageUnit.getHebrew();
        this.voice = voice + " " + languageUnit.getTranscription();
    }

}
