package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

public class AnyTimeQuestionPhraseBuilder extends AbstractVerbPhraseBuilder {

    private int time;

    public AnyTimeQuestionPhraseBuilder(int time){
        super();
        this.time = time;
    }
    @Override

    public ViewPhrase getPhrase(Verb verb) {
        Pronoun thirdPersonPronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD);
        Pronoun pronoun = pronounService.getRandomPronounByVerb(verb, time);

        List<LanguageUnit> tempCollection = new ArrayList<>();
        tempCollection.add(whoQuestion);

        tempCollection.add(verbService.getLanguageUnit(verb, thirdPersonPronoun, time));
        tempCollection.add(questionSign);
        tempCollection.add(pronoun.getLanguageUnit());
        tempCollection.add(verbService.getLanguageUnit(verb, pronoun, time));
        tempCollection.add(dot);
        return buildPhrase(tempCollection);
    }
}
