package il.george_nika.phrase2.service.phrase;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

public class AnyTimeQuestionPhraseBuilder extends AbstractPhraseBuilder {

    private int time;

    public AnyTimeQuestionPhraseBuilder(int time){
        super();
        this.time = time;
    }
    @Override

    public ViewPhrase getPhrase(Verb verb) {
        Pronoun thirdPersonPronoun = phraseBuilderService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD);
        Pronoun pronoun = phraseBuilderService.getPronounByVerb(verb, time);

        List<LanguageUnit> tempCollection = new ArrayList<LanguageUnit>();
        tempCollection.add(whoQuestion);

        tempCollection.add(phraseBuilderService.getLanguageUnitFromVerb(verb, thirdPersonPronoun, time));
        tempCollection.add(questionSign);
        tempCollection.add(pronoun.getLanguageUnit());
        tempCollection.add(phraseBuilderService.getLanguageUnitFromVerb(verb, pronoun, time));
        tempCollection.add(dot);
        return buildPhrase(tempCollection);
    }
}
