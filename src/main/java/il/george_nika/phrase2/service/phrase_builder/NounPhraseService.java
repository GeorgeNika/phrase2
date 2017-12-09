package il.george_nika.phrase2.service.phrase_builder;

import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.phrase_builder.noun.NounWithAdjectivePhraseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NounPhraseService {

    private final NounWithAdjectivePhraseBuilder nounWithAdjectivePhraseBuilder;

    @Autowired
    public NounPhraseService(NounWithAdjectivePhraseBuilder nounWithAdjectivePhraseBuilder) {
        this.nounWithAdjectivePhraseBuilder = nounWithAdjectivePhraseBuilder;
    }

    public ViewPhrase getPhrase(){

        return nounWithAdjectivePhraseBuilder.getPhrase();
    }

}
