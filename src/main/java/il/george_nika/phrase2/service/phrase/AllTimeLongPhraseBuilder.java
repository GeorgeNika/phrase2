package il.george_nika.phrase2.service.phrase;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

public class AllTimeLongPhraseBuilder extends AbstractPhraseBuilder {
    @Override
    public ViewPhrase getPhrase(Verb verb) {

        List<LanguageUnit> tempCollection = new ArrayList<LanguageUnit>();

        Pronoun infinitivePronoun = phraseBuilderService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST);
        Verb actionVerb = phraseBuilderService.getRandomActionVerb();

        tempCollection.add(infinitivePronoun.getLanguageUnit());
        tempCollection.add(phraseBuilderService.getLanguageUnitFromVerb(actionVerb, infinitivePronoun, TIME_PRESENT));
        tempCollection.add(verb.getInfinitive());
        tempCollection.add(comma);

        tempCollection.addAll(getCurrentPart(verb));
        tempCollection.addAll(getFuturePart(verb));
        tempCollection.addAll(getPastPart(verb));
        return buildPhrase(tempCollection);
    }

    private List<LanguageUnit> getCurrentPart(Verb verb){
        List<LanguageUnit> tempCollection = new ArrayList<LanguageUnit>();

        Pronoun currentFirstPronoun = phraseBuilderService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND);
        Pronoun currentSecondPronoun = phraseBuilderService.getPronoun(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_SECOND);
        Pronoun currentThirdPronoun = phraseBuilderService.getPronoun(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_FIRST);

        tempCollection.add(today);
        tempCollection.addAll(getVerbWithPronoun(verb, currentFirstPronoun, TIME_PRESENT));
        tempCollection.addAll(getVerbWithPronoun(verb, currentSecondPronoun, TIME_PRESENT));
        tempCollection.addAll(getVerbWithPronoun(verb, currentThirdPronoun, TIME_PRESENT));
        tempCollection.add(comma);

        return tempCollection;
    }

    private List<LanguageUnit> getFuturePart(Verb verb){
        List<LanguageUnit> tempCollection = new ArrayList<LanguageUnit>();

        Pronoun futureFirstPronoun = phraseBuilderService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST);
        Pronoun futureSecondPronoun = phraseBuilderService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND);
        Pronoun futureThirdPronoun = phraseBuilderService.getPronoun(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_SECOND);
        Pronoun futureFourthPronoun = phraseBuilderService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD);
        Pronoun futureFifthPronoun = phraseBuilderService.getPronoun(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_FIRST);

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
        List<LanguageUnit> tempCollection = new ArrayList<LanguageUnit>();

        Pronoun pastFirstPronoun = phraseBuilderService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD);
        Pronoun pastSecondPronoun = phraseBuilderService.getPronoun(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_THIRD);
        Pronoun pastThirdPronoun = phraseBuilderService.getPronoun(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_THIRD);
        Pronoun pastFourthPronoun = phraseBuilderService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST);

        tempCollection.add(yesterday);
        tempCollection.addAll(getVerbWithPronoun(verb, pastFirstPronoun, TIME_PAST));
        tempCollection.addAll(getVerbWithPronoun(verb, pastSecondPronoun, TIME_PAST));
        tempCollection.addAll(getVerbWithPronoun(verb, pastThirdPronoun, TIME_PAST));
        tempCollection.addAll(getVerbWithPronoun(verb, pastFourthPronoun, TIME_PAST));
        tempCollection.add(dot);

        return tempCollection;
    }

    private List<LanguageUnit> getVerbWithPronoun(Verb verb, Pronoun pronoun, int time){
        List<LanguageUnit> tempCollection = new ArrayList<LanguageUnit>();

        if (phraseBuilderService.isUnitExist(verb, pronoun, time)){
            tempCollection.add(pronoun.getLanguageUnit());
            tempCollection.add(phraseBuilderService.getLanguageUnitFromVerb(verb, pronoun, time));
        }
        return tempCollection;
    }

}
