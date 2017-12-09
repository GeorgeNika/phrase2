package il.george_nika.phrase2.service.phrase_builder;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.adjective.Adjective;
import il.george_nika.phrase2.model.noun.NounData;
import il.george_nika.phrase2.service.data.AdjectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdjectivePhraseService {

    private final AdjectiveService adjectiveService;

    @Autowired
    public AdjectivePhraseService(AdjectiveService adjectiveService) {
        this.adjectiveService = adjectiveService;
    }

    public LanguageUnit getMixedAdjective (Adjective adjective, NounData nounData){
        LanguageUnit mixedAdjective = new LanguageUnit();
        LanguageUnit ruLanguageUnit = adjectiveService.getLanguageUnitByGenderByQuantity(adjective,
                nounData.getGenderRU(), nounData.getQuantityRU());
        LanguageUnit ilLanguageUnit = adjectiveService.getLanguageUnitByGenderByQuantity(adjective,
                nounData.getGenderIL(), nounData.getQuantityIL());
        mixedAdjective.setRussian(ruLanguageUnit.getRussian());
        mixedAdjective.setHebrew(ilLanguageUnit.getHebrew());
        mixedAdjective.setTranscription(ilLanguageUnit.getTranscription());
        return  mixedAdjective;
    }
}
