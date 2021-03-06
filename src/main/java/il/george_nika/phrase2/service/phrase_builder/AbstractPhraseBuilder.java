package il.george_nika.phrase2.service.phrase_builder;

import il.george_nika.phrase2.model.data.LanguageUnit;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.model.view.WordIdentification;

import java.util.List;

abstract public class AbstractPhraseBuilder {

    protected static final LanguageUnit dot = new LanguageUnit(". ", ". ", ". ");
    protected static final LanguageUnit comma = new LanguageUnit(", ", ", ", ", ");

    protected ViewPhrase buildPhrase(List<LanguageUnit> languageUnits, List<WordIdentification> wordsIdentification) {
        ViewPhrase result = new ViewPhrase();
        for (LanguageUnit loopLU : languageUnits) {
            result.addLanguageUnit(loopLU);
        }
        result.setWordsIdentification(wordsIdentification);
        return result;
    }
}
