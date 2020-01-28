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

import static il.george_nika.phrase2.model.ModelConstants.TIME_PRESENT;
import static il.george_nika.phrase2.model.ModelConstants.VERB_TYPE;

@Component
public class PresentInfinitivePhraseBuilder extends AbstractVerbPhraseBuilder {

    public PresentInfinitivePhraseBuilder(PronounService pronounService, VerbService verbService) {
        super(pronounService, verbService);
    }

    @Override
    public ViewPhrase getPhrase(Verb verb) {
        Verb actionVerb = verbService.getRandomActionVerb();
        Pronoun firstPronoun = pronounService.getRandomPronounByVerb(actionVerb, TIME_PRESENT);
        Pronoun secondPronoun = pronounService.getRandomPronounByVerb(verb, TIME_PRESENT);

        List<WordIdentification> wordsIdentification = new ArrayList<>();
        wordsIdentification.add(new WordIdentification(VERB_TYPE, actionVerb.getId(), actionVerb.getInfinitive()));
        wordsIdentification.add(new WordIdentification(VERB_TYPE, verb.getId(), verb.getInfinitive()));

        List<LanguageUnit> resultCollection = new ArrayList<>();
        resultCollection.add(firstPronoun.getLanguageUnit());
        resultCollection.add(verbService.getLanguageUnitByPronounByTime(actionVerb, firstPronoun, TIME_PRESENT));
        resultCollection.add(verb.getInfinitive());
        resultCollection.add(comma);
        resultCollection.add(secondPronoun.getLanguageUnit());
        resultCollection.add(verbService.getLanguageUnitByPronounByTime(verb, secondPronoun, TIME_PRESENT));

        return buildPhrase(resultCollection, wordsIdentification);
    }
}
