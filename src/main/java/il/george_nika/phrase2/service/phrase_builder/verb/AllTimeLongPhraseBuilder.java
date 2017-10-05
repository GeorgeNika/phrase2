package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

public class AllTimeLongPhraseBuilder extends AbstractVerbPhraseBuilder {
    @Override
    public ViewPhrase getPhrase(Verb verb) {

        List<LanguageUnit> tempCollection = new ArrayList<>();

        Pronoun infinitivePronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST);
        Verb actionVerb = verbService.getRandomActionVerb();

        tempCollection.add(infinitivePronoun.getLanguageUnit());
        tempCollection.add(verbService.getLanguageUnit(actionVerb, infinitivePronoun, TIME_PRESENT));
        tempCollection.add(verb.getInfinitive());
        tempCollection.add(comma);

        tempCollection.addAll(getCurrentPart(verb));
        tempCollection.addAll(getFuturePart(verb));
        tempCollection.addAll(getPastPart(verb));
        return buildPhrase(tempCollection);
    }

    private List<LanguageUnit> getCurrentPart(Verb verb){
        List<LanguageUnit> tempCollection = new ArrayList<>();

        Pronoun currentFirstPronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND);
        Pronoun currentSecondPronoun = pronounService.getPronoun(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_SECOND);
        Pronoun currentThirdPronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_FIRST);

        tempCollection.add(today);
        tempCollection.addAll(getVerbWithPronoun(verb, currentFirstPronoun, TIME_PRESENT));
        tempCollection.addAll(getVerbWithPronoun(verb, currentSecondPronoun, TIME_PRESENT));
        tempCollection.addAll(getVerbWithPronoun(verb, currentThirdPronoun, TIME_PRESENT));
        tempCollection.add(comma);

        return tempCollection;
    }

    private List<LanguageUnit> getFuturePart(Verb verb){
        List<LanguageUnit> tempCollection = new ArrayList<>();

        Pronoun futureFirstPronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST);
        Pronoun futureSecondPronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND);
        Pronoun futureThirdPronoun = pronounService.getPronoun(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_SECOND);
        Pronoun futureFourthPronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD);
        Pronoun futureFifthPronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_FIRST);

        tempCollection.add(tomorrow);
        tempCollection.addAll(getVerbWithPronoun(verb, futureFirstPronoun, TIME_FUTURE));
        tempCollection.addAll(getVerbWithPronoun(verb, futureSecondPronoun, TIME_FUTURE));
        tempCollection.addAll(getVerbWithPronoun(verb, futureThirdPronoun, TIME_FUTURE));
        tempCollection.addAll(getVerbWithPronoun(verb, futureFourthPronoun, TIME_FUTURE));
        tempCollection.addAll(getVerbWithPronoun(verb, futureFifthPronoun, TIME_FUTURE));
        tempCollection.add(comma);

        return tempCollection;
    }

    private List<LanguageUnit> getPastPart(Verb verb){
        List<LanguageUnit> tempCollection = new ArrayList<>();

        Pronoun pastFirstPronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD);
        Pronoun pastSecondPronoun = pronounService.getPronoun(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_THIRD);
        Pronoun pastThirdPronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_THIRD);
        Pronoun pastFourthPronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST);

        tempCollection.add(yesterday);
        tempCollection.addAll(getVerbWithPronoun(verb, pastFirstPronoun, TIME_PAST));
        tempCollection.addAll(getVerbWithPronoun(verb, pastSecondPronoun, TIME_PAST));
        tempCollection.addAll(getVerbWithPronoun(verb, pastThirdPronoun, TIME_PAST));
        tempCollection.addAll(getVerbWithPronoun(verb, pastFourthPronoun, TIME_PAST));
        tempCollection.add(dot);

        return tempCollection;
    }

    private List<LanguageUnit> getVerbWithPronoun(Verb verb, Pronoun pronoun, int time){
        List<LanguageUnit> tempCollection = new ArrayList<>();

        if (verbService.isUnitExist(verb, pronoun, time)){
            tempCollection.add(pronoun.getLanguageUnit());
            tempCollection.add(verbService.getLanguageUnit(verb, pronoun, time));
        }
        return tempCollection;
    }

}
