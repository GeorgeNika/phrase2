package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.data.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.data.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.model.view.WordIdentification;
import il.george_nika.phrase2.service.data.PronounService;
import il.george_nika.phrase2.service.data.VerbService;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

abstract public class AbstractWhoQuestionPhraseBuilder extends AbstractVerbPhraseBuilder {

    private int time;

    public AbstractWhoQuestionPhraseBuilder(PronounService pronounService, VerbService verbService) {
        super(pronounService, verbService);
    }

    @Override
    public ViewPhrase getPhrase(Verb verb) {
        Pronoun thirdPersonPronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD);
        Pronoun pronoun = pronounService.getRandomPronounByVerb(verb, time);

        List<WordIdentification> wordsIdentification = new ArrayList<>();
        wordsIdentification.add(new WordIdentification(VERB_TYPE, verb.getId(), verb.getInfinitive()));

        List<LanguageUnit> resultCollection = new ArrayList<>();
        resultCollection.add(whoQuestion);

        resultCollection.add(verbService.getLanguageUnitByPronounByTime(verb, thirdPersonPronoun, time));
        resultCollection.add(questionSign);
        resultCollection.add(pronoun.getLanguageUnit());
        resultCollection.add(verbService.getLanguageUnitByPronounByTime(verb, pronoun, time));
        resultCollection.add(dot);
        return buildPhrase(resultCollection, wordsIdentification);
    }
}
