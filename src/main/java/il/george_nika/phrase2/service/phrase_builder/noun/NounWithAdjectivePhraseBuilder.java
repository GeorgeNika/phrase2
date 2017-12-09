package il.george_nika.phrase2.service.phrase_builder.noun;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.adjective.Adjective;
import il.george_nika.phrase2.model.noun.Noun;
import il.george_nika.phrase2.model.noun.NounData;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.model.view.WordIdentification;
import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.data.AdjectiveService;
import il.george_nika.phrase2.service.data.NounService;
import il.george_nika.phrase2.service.data.PronounService;
import il.george_nika.phrase2.service.phrase_builder.AbstractPhraseBuilder;
import il.george_nika.phrase2.service.phrase_builder.AdjectivePhraseService;
import il.george_nika.phrase2.service.phrase_builder.NounPhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.ADJECTIVE_TYPE;
import static il.george_nika.phrase2.model.ModelConstants.NOUN_TYPE;

@Component
public class NounWithAdjectivePhraseBuilder extends AbstractPhraseBuilder {

    private final RandomService randomService;
    private final NounService nounService;
    private final AdjectiveService adjectiveService;

    private final AdjectivePhraseService adjectivePhraseService;

    @Autowired
    public NounWithAdjectivePhraseBuilder(RandomService randomService, NounService nounService,
                                          AdjectiveService adjectiveService, AdjectivePhraseService adjectivePhraseService) {
        this.randomService = randomService;
        this.nounService = nounService;
        this.adjectiveService = adjectiveService;
        this.adjectivePhraseService = adjectivePhraseService;
    }

    public ViewPhrase getPhrase(){
        List<LanguageUnit> resultCollection = new ArrayList<>();
        List<WordIdentification> wordsIdentification = new ArrayList<>();

        Noun noun = nounService.getRandomNoun();
        Adjective adjective = adjectiveService.getRandomAdjective();
        wordsIdentification.add(new WordIdentification(NOUN_TYPE, noun.getId(), noun.getMainForm()));
        wordsIdentification.add(new WordIdentification(ADJECTIVE_TYPE, adjective.getId(), adjective.getMainForm()));

        int tempIndex = randomService.getRandom(noun.getNounDataCollection().size());
        NounData selectedNounData = noun.getNounDataCollection().get(tempIndex);
        resultCollection.add(selectedNounData.getLanguageUnit());

        resultCollection.add(adjectivePhraseService.getMixedAdjective(adjective, selectedNounData));

        return buildPhrase(resultCollection, wordsIdentification);
    }

}
