package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.TIME_PRESENT;

public class PresentInfinitivePhraseBuilder extends AbstractVerbPhraseBuilder {

    @Override
    public ViewPhrase getPhrase(Verb verb) {
        Verb actionVerb = verbService.getRandomActionVerb();
        Pronoun firstPronoun = pronounService.getRandomPronounByVerb(actionVerb, TIME_PRESENT);
        Pronoun secondPronoun = pronounService.getRandomPronounByVerb(verb, TIME_PRESENT);

        List<LanguageUnit> tempCollection = new ArrayList<>();
        tempCollection.add(firstPronoun.getLanguageUnit());
        tempCollection.add(verbService.getLanguageUnitByPronounByTime(actionVerb, firstPronoun, TIME_PRESENT));
        tempCollection.add(verb.getInfinitive());
        tempCollection.add(comma);
        tempCollection.add(secondPronoun.getLanguageUnit());
        tempCollection.add(verbService.getLanguageUnitByPronounByTime(verb, secondPronoun, TIME_PRESENT));

        return buildPhrase(tempCollection);
    }
}
