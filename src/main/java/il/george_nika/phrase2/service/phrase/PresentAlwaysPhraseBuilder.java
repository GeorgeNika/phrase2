package il.george_nika.phrase2.service.phrase;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.ModelConstants;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

public class PresentAlwaysPhraseBuilder extends AbstractPhraseBuilder {

    @Override
    public ViewPhrase getPhrase(Verb verb) {

        Pronoun pronoun = phraseBuilderService.getPronounByVerb(verb, ModelConstants.TIME_PRESENT);

        List<LanguageUnit> tempCollection = new ArrayList<LanguageUnit>();
        tempCollection.add(pronoun.getLanguageUnit());
        tempCollection.add(always);
        tempCollection.add(phraseBuilderService.getLanguageUnitFromVerb(verb, pronoun, ModelConstants.TIME_PRESENT));
        tempCollection.add(dot);

        return buildPhrase(tempCollection);

    }
}
