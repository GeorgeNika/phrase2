package il.george_nika.phrase2.service.phrase_builder.noun;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.adjective.Adjective;
import il.george_nika.phrase2.model.noun.Noun;
import il.george_nika.phrase2.model.noun.NounData;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.data.AdjectiveService;
import il.george_nika.phrase2.service.data.NounService;
import il.george_nika.phrase2.service.data.PronounService;
import il.george_nika.phrase2.service.phrase_builder.AbstractPhraseBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class NounWithAdjectivePhraseBuilder extends AbstractPhraseBuilder {

    @Autowired
    protected RandomService randomService;

    @Autowired
    protected NounService nounService;

    @Autowired
    protected AdjectiveService adjectiveService;

    public ViewPhrase getPhrase(){
        List<LanguageUnit> tempCollection = new ArrayList<>();

        Noun noun = nounService.getRandomNoun();
        Adjective adjective = adjectiveService.getRandomAdjective();

        int tempIndex = randomService.getRandom(noun.getNounDataCollection().size());
        NounData selectedNounData = noun.getNounDataCollection().get(tempIndex);
        tempCollection.add(selectedNounData.getLanguageUnit());

        LanguageUnit mixedAdjective = new LanguageUnit();
        LanguageUnit ruLanguageUnit = adjectiveService.getLanguageUnitByGenderByQuantity(adjective,
                selectedNounData.getGenderRU(), selectedNounData.getQuantityRU());
        LanguageUnit ilLanguageUnit = adjectiveService.getLanguageUnitByGenderByQuantity(adjective,
                selectedNounData.getGenderIL(), selectedNounData.getQuantityIL());
        mixedAdjective.setRussian(ruLanguageUnit.getRussian());
        mixedAdjective.setHebrew(ilLanguageUnit.getHebrew());
        mixedAdjective.setTranscription(ilLanguageUnit.getTranscription());
        tempCollection.add(mixedAdjective);

        return buildPhrase(tempCollection);
    }


}
