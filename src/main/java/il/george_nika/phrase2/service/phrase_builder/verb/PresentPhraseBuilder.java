package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.ModelConstants;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
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
public class PresentPhraseBuilder extends AbstractVerbPhraseBuilder {

    public PresentPhraseBuilder(PronounService pronounService, VerbService verbService) {
        super(pronounService, verbService);
    }

    @Override
    public ViewPhrase getPhrase(Verb verb) {
        Pronoun firstPronoun = pronounService.getRandomPronounByVerb(verb, ModelConstants.TIME_PRESENT);
        Pronoun secondPronoun = pronounService.getRandomPronounByVerb(verb, ModelConstants.TIME_PRESENT);

        List<WordIdentification> wordsIdentification = new ArrayList<>();
        wordsIdentification.add(new WordIdentification(VERB_TYPE, verb.getId(), verb.getInfinitive()));

        List<LanguageUnit> resultCollection = new ArrayList<>();
        resultCollection.add(firstPronoun.getLanguageUnit());
        resultCollection.add(verbService.getLanguageUnitByPronounByTime(verb, firstPronoun, TIME_PRESENT));
        resultCollection.add(comma);
        resultCollection.add(secondPronoun.getLanguageUnit());
        resultCollection.add(verbService.getLanguageUnitByPronounByTime(verb, secondPronoun, TIME_PRESENT));

        return buildPhrase(resultCollection, wordsIdentification);
    }
}
