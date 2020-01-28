package il.george_nika.phrase2.model.view;

import il.george_nika.phrase2.model.data.LanguageUnit;
import lombok.Data;

@Data
public class WordIdentification {

    private String type;
    private int wordId;
    private LanguageUnit languageUnit;

    public WordIdentification(){
        this.type = "";
        this.languageUnit = new LanguageUnit();
    }

    public WordIdentification(String type, int wordId, LanguageUnit languageUnit) {
        this.type = type;
        this.wordId = wordId;
        this.languageUnit = languageUnit;
    }
}
