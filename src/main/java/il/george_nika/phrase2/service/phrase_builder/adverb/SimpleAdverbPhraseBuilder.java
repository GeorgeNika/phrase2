package il.george_nika.phrase2.service.phrase_builder.adverb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.adjective.Adjective;
import il.george_nika.phrase2.model.adverb.Adverb;
import il.george_nika.phrase2.model.noun.Noun;
import il.george_nika.phrase2.model.noun.NounData;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.model.view.WordIdentification;
import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.data.*;
import il.george_nika.phrase2.service.phrase_builder.AbstractPhraseBuilder;
import il.george_nika.phrase2.service.phrase_builder.AdjectivePhraseService;
import il.george_nika.phrase2.service.phrase_builder.sentence.SentencePhraseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

@Component
public class SimpleAdverbPhraseBuilder extends AbstractPhraseBuilder {

    private final VerbService verbService;
    private final PronounService pronounService;
    private final AdverbService adverbService;

    protected static final LanguageUnit questionSign = new LanguageUnit("? ", "? ", "? ");
    protected static final LanguageUnit howQuestion = new LanguageUnit("Как ", "איך ", "эйх ");

    @Autowired
    public SimpleAdverbPhraseBuilder(PronounService pronounService, VerbService verbService,
                                     AdverbService adverbService) {
        this.verbService = verbService;
        this.pronounService = pronounService;
        this.adverbService = adverbService;
    }

    public ViewPhrase getPhrase() {

        List<LanguageUnit> resultCollection = new ArrayList<>();
        List<WordIdentification> wordsIdentification = new ArrayList<>();

        Verb actionVerb = verbService.getRandomActionVerb();
        Verb verb = verbService.getRandomVerb();
        Pronoun pronoun = pronounService.getRandomPronounByVerb(actionVerb, TIME_PRESENT);
        Adverb adverb = adverbService.getRandomAdverb();

        wordsIdentification.add(new WordIdentification(VERB_TYPE, actionVerb.getId(), actionVerb.getInfinitive()));
        wordsIdentification.add(new WordIdentification(VERB_TYPE, verb.getId(), verb.getInfinitive()));
        wordsIdentification.add(new WordIdentification(ADVERB_TYPE, adverb.getId(), adverb.getMainForm()));

        resultCollection.add(howQuestion);
        resultCollection.add(pronoun.getLanguageUnit());
        resultCollection.add(verbService.getLanguageUnitByPronounByTime(actionVerb, pronoun, TIME_PRESENT));
        resultCollection.add(verb.getInfinitive());

        resultCollection.add(questionSign);

        resultCollection.add(adverb.getMainForm());
        resultCollection.add(dot);

        return buildPhrase(resultCollection, wordsIdentification);
    }
}
