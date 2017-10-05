package il.george_nika.phrase2.service.phrase_builder;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.List;

/**
 * Created by George on 04.10.2017.
 */
abstract public class AbstractPhraseBuilder {

    protected ViewPhrase buildPhrase(List<LanguageUnit> languageUnits) {
        ViewPhrase result = new ViewPhrase();
        for (LanguageUnit loopLU : languageUnits) {
            result.addLanguageUnit(loopLU);
        }
        return result;
    }
}
