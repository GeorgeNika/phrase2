package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.ModelConstants;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.TIME_PRESENT;

public class PresentPhraseBuilder extends AbstractVerbPhraseBuilder {

    @Override
    public ViewPhrase getPhrase(Verb verb) {
        Pronoun firstPronoun = pronounService.getPronounByVerb(verb, ModelConstants.TIME_PRESENT);
        Pronoun secondPronoun = pronounService.getPronounByVerb(verb, ModelConstants.TIME_PRESENT);

        List<LanguageUnit> tempCollection = new ArrayList<>();
        tempCollection.add(firstPronoun.getLanguageUnit());
        tempCollection.add(verbService.getLanguageUnit(verb, firstPronoun, TIME_PRESENT));
        tempCollection.add(comma);
        tempCollection.add(secondPronoun.getLanguageUnit());
        tempCollection.add(verbService.getLanguageUnit(verb, secondPronoun, TIME_PRESENT));

        return buildPhrase(tempCollection);
    }
}