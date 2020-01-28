package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.data.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.data.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.model.view.WordIdentification;
import il.george_nika.phrase2.service.data.PronounService;
import il.george_nika.phrase2.service.data.VerbService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

@Component
public class AllTimeLongPhraseBuilder extends AbstractVerbPhraseBuilder {
    public AllTimeLongPhraseBuilder(PronounService pronounService, VerbService verbService) {
        super(pronounService, verbService);
    }

    @Override
    public ViewPhrase getPhrase(Verb verb) {

        List<LanguageUnit> resultCollection = new ArrayList<>();
        List<WordIdentification> wordsIdentification = new ArrayList<>();

        Pronoun infinitivePronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST);
        Verb actionVerb = verbService.getRandomActionVerb();
        wordsIdentification.add(new WordIdentification(VERB_TYPE, actionVerb.getId(), actionVerb.getInfinitive()));
        wordsIdentification.add(new WordIdentification(VERB_TYPE, verb.getId(), verb.getInfinitive()));

        resultCollection.add(infinitivePronoun.getLanguageUnit());
        resultCollection.add(verbService.getLanguageUnitByPronounByTime(actionVerb, infinitivePronoun, TIME_PRESENT));
        resultCollection.add(verb.getInfinitive());
        resultCollection.add(comma);

        resultCollection.addAll(getTimePart(verb, TIME_PRESENT, today));
        resultCollection.addAll(getTimePart(verb, TIME_PAST, yesterday));
        resultCollection.addAll(getTimePart(verb, TIME_FUTURE, tomorrow));
        return buildPhrase(resultCollection, wordsIdentification);
    }

    private List<LanguageUnit> getTimePart(Verb verb, int time, LanguageUnit description){
        List<LanguageUnit> resultCollection = new ArrayList<>();

        Pronoun pronoun = pronounService.getRandomPronounByVerb(verb, time);

        resultCollection.add(description);
        resultCollection.addAll(getVerbWithPronoun(verb, pronoun, time));
        resultCollection.add(comma);

        return resultCollection;
    }


    private List<LanguageUnit> getVerbWithPronoun(Verb verb, Pronoun pronoun, int time){
        List<LanguageUnit> resultCollection = new ArrayList<>();

        if (verbService.isUnitExist(verb, pronoun, time)){
            resultCollection.add(pronoun.getLanguageUnit());
            resultCollection.add(verbService.getLanguageUnitByPronounByTime(verb, pronoun, time));
        }
        return resultCollection;
    }

}
