package il.george_nika.phrase2.service.phrase;

import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;

/**
 * Created by George on 07.07.2017.
 */
public interface PhraseBuilder {

    ViewPhrase getPhrase(Verb verb);
}
