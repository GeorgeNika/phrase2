package il.george_nika.phrase2.service.phrase_builder;

import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.data.VerbService;
import il.george_nika.phrase2.service.phrase_builder.verb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

@Service
public class VerbPhraseService {

    @Autowired
    private VerbService verbService;

    @Autowired
    private RandomService randomService;

    private @Autowired
    AutowireCapableBeanFactory beanFactory;

    public ViewPhrase getSimplePhrase(){

        Verb verb = verbService.getRandomVerb();
        List<VerbPhraseBuilder> verbPhraseBuilders = new ArrayList<>();

        verbPhraseBuilders.add(new AllTimeLongPhraseBuilder());

        if (verbService.isTimeExist(verb, TIME_PAST)){
            verbPhraseBuilders.add(new PastAlreadyPhraseBuilder());
            if (verbService.isUnitExist(verb, GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD, TIME_PAST)){
                verbPhraseBuilders.add(new AnyTimeQuestionPhraseBuilder(TIME_PAST));
            }
        }

        if (verbService.isTimeExist(verb, TIME_PRESENT)){
            verbPhraseBuilders.add(new PresentInfinitivePhraseBuilder());
            verbPhraseBuilders.add(new PresentPhraseBuilder());
            verbPhraseBuilders.add(new PresentAlwaysPhraseBuilder());
            if (verbService.isUnitExist(verb, GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD, TIME_PRESENT)){
                verbPhraseBuilders.add(new AnyTimeQuestionPhraseBuilder(TIME_PRESENT));
            }
        }

        if (verbService.isTimeExist(verb, TIME_FUTURE)){
            verbPhraseBuilders.add(new FuturePhraseBuilder());
            if (verbService.isUnitExist(verb, GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD, TIME_FUTURE)){
                verbPhraseBuilders.add(new AnyTimeQuestionPhraseBuilder(TIME_FUTURE));
            }
        }

        for (VerbPhraseBuilder loopVerbPhraseBuilder : verbPhraseBuilders){
            beanFactory.autowireBean(loopVerbPhraseBuilder);
        }

        return verbPhraseBuilders.get(randomService.getRandom(verbPhraseBuilders.size())).getPhrase(verb);
    }

}
