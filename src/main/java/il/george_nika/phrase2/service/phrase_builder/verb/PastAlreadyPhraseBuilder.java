package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.TIME_PAST;

public class PastAlreadyPhraseBuilder extends AbstractVerbPhraseBuilder {

    @Override
    public ViewPhrase getPhrase(Verb verb) {
        Pronoun firstPronoun = pronounService.getPronounByVerb(verb, TIME_PAST);
        Pronoun secondPronoun = pronounService.getPronounByVerb(verb, TIME_PAST);

        List<LanguageUnit> tempCollection = new ArrayList<>();
        tempCollection.add(firstPronoun.getLanguageUnit());
        tempCollection.add(already);
        tempCollection.add(verbService.getLanguageUnit(verb, firstPronoun, TIME_PAST));
        tempCollection.add(comma);
        tempCollection.add(secondPronoun.getLanguageUnit());
        tempCollection.add(notYet);
        tempCollection.add(verbService.getLanguageUnit(verb, secondPronoun, TIME_PAST));

        return buildPhrase(tempCollection);
    }
}
