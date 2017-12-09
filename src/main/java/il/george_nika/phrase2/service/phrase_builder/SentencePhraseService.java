package il.george_nika.phrase2.service.phrase_builder;

import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.phrase_builder.sentence.SimpleSentencePhraseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentencePhraseService {

    private final SimpleSentencePhraseBuilder simpleSentencePhraseBuilder;

    @Autowired
    public SentencePhraseService(SimpleSentencePhraseBuilder simpleSentencePhraseBuilder) {
        this.simpleSentencePhraseBuilder = simpleSentencePhraseBuilder;
    }

    public ViewPhrase getPhrase(){


        return simpleSentencePhraseBuilder.getPhrase();
    }
}
