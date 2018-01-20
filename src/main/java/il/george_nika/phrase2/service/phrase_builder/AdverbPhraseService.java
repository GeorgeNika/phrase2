package il.george_nika.phrase2.service.phrase_builder;

import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.phrase_builder.adverb.SimpleAdverbPhraseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdverbPhraseService {

    private final SimpleAdverbPhraseBuilder simpleAdverbPhraseBuilder;

    @Autowired
    public AdverbPhraseService(SimpleAdverbPhraseBuilder simpleAdverbPhraseBuilder) {
        this.simpleAdverbPhraseBuilder = simpleAdverbPhraseBuilder;
    }

    public ViewPhrase getPhrase(){

        return simpleAdverbPhraseBuilder.getPhrase();
    }
}
