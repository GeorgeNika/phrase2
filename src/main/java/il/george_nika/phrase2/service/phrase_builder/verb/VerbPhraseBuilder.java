package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;

/**
 * Created by George on 07.07.2017.
 */
public interface VerbPhraseBuilder {

    ViewPhrase getPhrase(Verb verb);
}
