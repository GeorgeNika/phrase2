package il.george_nika.phrase2.service.phrase_builder.sentence;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.adjective.Adjective;
import il.george_nika.phrase2.model.noun.Noun;
import il.george_nika.phrase2.model.noun.NounData;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.model.view.WordIdentification;
import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.data.AdjectiveService;
import il.george_nika.phrase2.service.data.NounService;
import il.george_nika.phrase2.service.data.PronounService;
import il.george_nika.phrase2.service.data.VerbService;
import il.george_nika.phrase2.service.phrase_builder.AbstractPhraseBuilder;
import il.george_nika.phrase2.service.phrase_builder.AdjectivePhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

@Component
public class SimpleSentencePhraseBuilder extends AbstractPhraseBuilder implements SentencePhraseBuilder {

    private final VerbService verbService;
    private final PronounService pronounService;
    private final NounService nounService;
    private final AdjectiveService adjectiveService;
    private final RandomService randomService;

    private final AdjectivePhraseService adjectivePhraseService;

    @Autowired
    public SimpleSentencePhraseBuilder(PronounService pronounService, VerbService verbService,
                                       NounService nounService, AdjectiveService adjectiveService,
                                       RandomService randomService, AdjectivePhraseService adjectivePhraseService) {
        this.verbService = verbService;
        this.pronounService = pronounService;
        this.nounService = nounService;
        this.adjectiveService = adjectiveService;
        this.randomService = randomService;
        this.adjectivePhraseService = adjectivePhraseService;
    }

    @Override
    public ViewPhrase getPhrase() {

        List<LanguageUnit> resultCollection = new ArrayList<>();
        List<WordIdentification> wordsIdentification = new ArrayList<>();

        Verb actionVerb = verbService.getRandomActionVerb();
        Verb verb = verbService.getRandomVerb();
        Pronoun pronoun = pronounService.getRandomPronounByVerb(actionVerb, TIME_PRESENT);
        Noun noun = nounService.getRandomNoun();
        Adjective adjective = adjectiveService.getRandomAdjective();

        wordsIdentification.add(new WordIdentification(VERB_TYPE, actionVerb.getId(), actionVerb.getInfinitive()));
        wordsIdentification.add(new WordIdentification(VERB_TYPE, verb.getId(), verb.getInfinitive()));
        wordsIdentification.add(new WordIdentification(NOUN_TYPE, noun.getId(), noun.getMainForm()));
        wordsIdentification.add(new WordIdentification(ADJECTIVE_TYPE, adjective.getId(), adjective.getMainForm()));

        resultCollection.add(pronoun.getLanguageUnit());
        resultCollection.add(verbService.getLanguageUnitByPronounByTime(actionVerb, pronoun, TIME_PRESENT));
        resultCollection.add(verb.getInfinitive());

        if (verb.getPreposition() != null){
            resultCollection.add(verb.getPreposition());
        }

        int tempIndex = randomService.getRandom(noun.getNounDataCollection().size());
        NounData selectedNounData = noun.getNounDataCollection().get(tempIndex);
        resultCollection.add(selectedNounData.getLanguageUnit());

        resultCollection.add(adjectivePhraseService.getMixedAdjective(adjective, selectedNounData));

        return buildPhrase(resultCollection, wordsIdentification);
    }
}
