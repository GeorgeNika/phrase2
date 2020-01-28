package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.data.LanguageUnit;
import il.george_nika.phrase2.model.ModelConstants;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.data.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.model.view.WordIdentification;
import il.george_nika.phrase2.service.data.PronounService;
import il.george_nika.phrase2.service.data.VerbService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.VERB_TYPE;

@Component
public class FuturePhraseBuilder extends AbstractVerbPhraseBuilder {

    public FuturePhraseBuilder(PronounService pronounService, VerbService verbService) {
        super(pronounService, verbService);
    }

    @Override
    public ViewPhrase getPhrase(Verb verb) {
        Pronoun pronoun = pronounService.getRandomPronounByVerb(verb, ModelConstants.TIME_FUTURE);

        List<WordIdentification> wordsIdentification = new ArrayList<>();
        wordsIdentification.add(new WordIdentification(VERB_TYPE, verb.getId(), verb.getInfinitive()));
        List<LanguageUnit> resultCollection = new ArrayList<>();
        resultCollection.add(whenQuestion);
        resultCollection.add(pronoun.getLanguageUnit());
        resultCollection.add(verbService.getLanguageUnitByPronounByTime(verb, pronoun, ModelConstants.TIME_FUTURE));
        resultCollection.add(questionSign);

        return buildPhrase(resultCollection, wordsIdentification);
    }
}
