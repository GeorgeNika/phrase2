package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.ModelConstants;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.model.view.WordIdentification;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.VERB_TYPE;


public class FuturePhraseBuilder extends AbstractVerbPhraseBuilder {

    @Override
    public ViewPhrase getPhrase(Verb verb) {
        Pronoun pronoun = pronounService.getRandomPronounByVerb(verb, ModelConstants.TIME_FUTURE);

        List<WordIdentification> wordsIdentification = new ArrayList<>();
        wordsIdentification.add(new WordIdentification(VERB_TYPE, verb.getId(), verb.getInfinitive()));
        List<LanguageUnit> tempCollection = new ArrayList<>();
        tempCollection.add(whenQuestion);
        tempCollection.add(pronoun.getLanguageUnit());
        tempCollection.add(verbService.getLanguageUnitByPronounByTime(verb, pronoun, ModelConstants.TIME_FUTURE));
        tempCollection.add(questionSign);

        return buildPhrase(tempCollection, wordsIdentification);
    }
}
