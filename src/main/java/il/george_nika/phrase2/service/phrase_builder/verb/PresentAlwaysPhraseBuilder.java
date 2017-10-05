package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.ModelConstants;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

public class PresentAlwaysPhraseBuilder extends AbstractVerbPhraseBuilder {

    @Override
    public ViewPhrase getPhrase(Verb verb) {

        Pronoun pronoun = pronounService.getPronounByVerb(verb, ModelConstants.TIME_PRESENT);

        List<LanguageUnit> tempCollection = new ArrayList<>();
        tempCollection.add(pronoun.getLanguageUnit());
        tempCollection.add(always);
        tempCollection.add(verbService.getLanguageUnit(verb, pronoun, ModelConstants.TIME_PRESENT));
        tempCollection.add(dot);

        return buildPhrase(tempCollection);
    }
}
