package il.george_nika.phrase2.service;

import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.phrase_builder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static il.george_nika.phrase2.model.ModelConstants.*;

@Service
public class PhraseService {

    private final VerbPhraseService verbPhraseService;
    private final NumberPhraseService numberPhraseService;
    private final NounPhraseService nounPhraseService;
    private final SentencePhraseService sentencePhraseService;
    private final AdverbPhraseService adverbPhraseService;

    @Autowired
    public PhraseService(VerbPhraseService verbPhraseService, NumberPhraseService numberPhraseService,
                         NounPhraseService nounPhraseService, SentencePhraseService sentencePhraseService,
                         AdverbPhraseService adverbPhraseService) {
        this.verbPhraseService = verbPhraseService;
        this.numberPhraseService = numberPhraseService;
        this.nounPhraseService = nounPhraseService;
        this.sentencePhraseService = sentencePhraseService;
        this.adverbPhraseService = adverbPhraseService;
    }


    public ViewPhrase getPhrase(String phraseType){
        if (phraseType.equals(SENTENCE_TYPE)) {
            return sentencePhraseService.getPhrase();
        }
        if (phraseType.equals(NOUN_TYPE)) {
            return nounPhraseService.getPhrase();
        }
        if (phraseType.equals(NUMBER_TYPE)) {
            return numberPhraseService.getPhrase();
        }
        if (phraseType.equals(FRACTIONAL_NUMBER_TYPE)) {
            return numberPhraseService.getFractionalPhrase();
        }
        if (phraseType.equals(ADVERB_TYPE)) {
            return adverbPhraseService.getPhrase();
        }
        if (phraseType.equals(VERB_TYPE)) {
            return verbPhraseService.getPhrase();
        }
        throw new RuntimeException("unknown type of phrase - " + phraseType);
    }

}
