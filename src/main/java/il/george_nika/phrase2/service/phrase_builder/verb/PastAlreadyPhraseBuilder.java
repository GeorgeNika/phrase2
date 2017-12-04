package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.model.view.WordIdentification;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.TIME_PAST;
import static il.george_nika.phrase2.model.ModelConstants.VERB_TYPE;

public class PastAlreadyPhraseBuilder extends AbstractVerbPhraseBuilder {

    @Override
    public ViewPhrase getPhrase(Verb verb) {
        Pronoun firstPronoun = pronounService.getRandomPronounByVerb(verb, TIME_PAST);
        Pronoun secondPronoun = pronounService.getRandomPronounByVerb(verb, TIME_PAST);

        List<WordIdentification> wordsIdentification = new ArrayList<>();
        wordsIdentification.add(new WordIdentification(VERB_TYPE, verb.getId(), verb.getInfinitive()));

        List<LanguageUnit> tempCollection = new ArrayList<>();
        tempCollection.add(firstPronoun.getLanguageUnit());
        tempCollection.add(already);
        tempCollection.add(verbService.getLanguageUnitByPronounByTime(verb, firstPronoun, TIME_PAST));
        tempCollection.add(comma);
        tempCollection.add(secondPronoun.getLanguageUnit());
        tempCollection.add(notYet);
        tempCollection.add(verbService.getLanguageUnitByPronounByTime(verb, secondPronoun, TIME_PAST));

        return buildPhrase(tempCollection, wordsIdentification);
    }
}
