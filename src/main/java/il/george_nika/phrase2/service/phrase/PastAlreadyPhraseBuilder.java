package il.george_nika.phrase2.service.phrase;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.TIME_PAST;

public class PastAlreadyPhraseBuilder extends AbstractPhraseBuilder {

    @Override
    public ViewPhrase getPhrase(Verb verb) {
        Pronoun firstPronoun = phraseBuilderService.getPronounByVerb(verb, TIME_PAST);
        Pronoun secondPronoun = phraseBuilderService.getPronounByVerb(verb, TIME_PAST);

        List<LanguageUnit> tempCollection = new ArrayList<LanguageUnit>();
        tempCollection.add(firstPronoun.getLanguageUnit());
        tempCollection.add(already);
        tempCollection.add(phraseBuilderService.getLanguageUnitFromVerb(verb, firstPronoun, TIME_PAST));
        tempCollection.add(comma);
        tempCollection.add(secondPronoun.getLanguageUnit());
        tempCollection.add(notYet);
        tempCollection.add(phraseBuilderService.getLanguageUnitFromVerb(verb, secondPronoun, TIME_PAST));

        return buildPhrase(tempCollection);
    }
}
