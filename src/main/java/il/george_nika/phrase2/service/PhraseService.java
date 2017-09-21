package il.george_nika.phrase2.service;

import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.phrase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

@Service
public class PhraseService {

    @Autowired
    private VerbService verbService;

    @Autowired
    private RandomService randomService;

    private @Autowired
    AutowireCapableBeanFactory beanFactory;

    public ViewPhrase getSimplePhrase(){

        Verb verb = verbService.getRandomVerb();
        List<PhraseBuilder> phraseBuilders = new ArrayList<PhraseBuilder>();

        phraseBuilders.add(new AllTimeLongPhraseBuilder());

        if (verbService.isTimeExist(verb, TIME_PAST)){
            phraseBuilders.add(new PastAlreadyPhraseBuilder());
            if (verbService.isUnitExist(verb, GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD, TIME_PAST)){
                phraseBuilders.add(new AnyTimeQuestionPhraseBuilder(TIME_PAST));
            }
        }

        if (verbService.isTimeExist(verb, TIME_PRESENT)){
            phraseBuilders.add(new PresentInfinitivePhraseBuilder());
            phraseBuilders.add(new PresentPhraseBuilder());
            phraseBuilders.add(new PresentAlwaysPhraseBuilder());
            if (verbService.isUnitExist(verb, GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD, TIME_PRESENT)){
                phraseBuilders.add(new AnyTimeQuestionPhraseBuilder(TIME_PRESENT));
            }
        }

        if (verbService.isTimeExist(verb, TIME_FUTURE)){
            phraseBuilders.add(new FuturePhraseBuilder());
            if (verbService.isUnitExist(verb, GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD, TIME_FUTURE)){
                phraseBuilders.add(new AnyTimeQuestionPhraseBuilder(TIME_FUTURE));
            }
        }

        for (PhraseBuilder loopPhraseBuilder: phraseBuilders){
            beanFactory.autowireBean(loopPhraseBuilder);
        }

        return phraseBuilders.get(randomService.getRandom(phraseBuilders.size())).getPhrase(verb);
    }

}
