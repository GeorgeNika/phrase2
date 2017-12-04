package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.model.view.WordIdentification;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

public class AllTimeLongPhraseBuilder extends AbstractVerbPhraseBuilder {
    @Override
    public ViewPhrase getPhrase(Verb verb) {

        List<LanguageUnit> tempCollection = new ArrayList<>();
        List<WordIdentification> wordsIdentification = new ArrayList<>();

        Pronoun infinitivePronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST);
        Verb actionVerb = verbService.getRandomActionVerb();
        wordsIdentification.add(new WordIdentification(VERB_TYPE, actionVerb.getId(), actionVerb.getInfinitive()));
        wordsIdentification.add(new WordIdentification(VERB_TYPE, verb.getId(), verb.getInfinitive()));

        tempCollection.add(infinitivePronoun.getLanguageUnit());
        tempCollection.add(verbService.getLanguageUnitByPronounByTime(actionVerb, infinitivePronoun, TIME_PRESENT));
        tempCollection.add(verb.getInfinitive());
        tempCollection.add(comma);

        tempCollection.addAll(getTimePart(verb, TIME_PRESENT, today));
        tempCollection.addAll(getTimePart(verb, TIME_PAST, yesterday));
        tempCollection.addAll(getTimePart(verb, TIME_FUTURE, tomorrow));
        return buildPhrase(tempCollection, wordsIdentification);
    }

    private List<LanguageUnit> getTimePart(Verb verb, int time, LanguageUnit description){
        List<LanguageUnit> tempCollection = new ArrayList<>();

        Pronoun pronoun = pronounService.getRandomPronounByVerb(verb, time);

        tempCollection.add(description);
        tempCollection.addAll(getVerbWithPronoun(verb, pronoun, time));
        tempCollection.add(comma);

        return tempCollection;
    }


    private List<LanguageUnit> getVerbWithPronoun(Verb verb, Pronoun pronoun, int time){
        List<LanguageUnit> tempCollection = new ArrayList<>();

        if (verbService.isUnitExist(verb, pronoun, time)){
            tempCollection.add(pronoun.getLanguageUnit());
            tempCollection.add(verbService.getLanguageUnitByPronounByTime(verb, pronoun, time));
        }
        return tempCollection;
    }

}
