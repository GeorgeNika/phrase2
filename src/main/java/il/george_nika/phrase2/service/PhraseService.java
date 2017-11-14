package il.george_nika.phrase2.service;

import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.phrase_builder.NounPhraseService;
import il.george_nika.phrase2.service.phrase_builder.NumberPhraseService;
import il.george_nika.phrase2.service.phrase_builder.VerbPhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by George on 04.10.2017.
 */
@Service
public class PhraseService {

    @Autowired
    private VerbPhraseService verbPhraseService;

    @Autowired
    private NumberPhraseService numberPhraseService;

    @Autowired
    private NounPhraseService nounPhraseService;


    public ViewPhrase getPhrase(String phraseType){
        if (phraseType.equals("noun")) {
            return nounPhraseService.getSimplePhrase();
        }
        if (phraseType.equals("number")) {
            return numberPhraseService.getSimplePhrase();
        }
        return verbPhraseService.getSimplePhrase();
    }
}
