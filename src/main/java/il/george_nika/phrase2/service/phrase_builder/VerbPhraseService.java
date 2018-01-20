package il.george_nika.phrase2.service.phrase_builder;

import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.data.VerbService;
import il.george_nika.phrase2.service.phrase_builder.verb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

@Service
public class VerbPhraseService {

    private final VerbService verbService;

    private final AllTimeLongPhraseBuilder allTimeLongPhraseBuilder;
    private final PastAlreadyPhraseBuilder pastAlreadyPhraseBuilder;
    private final PresentWhoQuestionPhraseBuilder presentWhoQuestionPhraseBuilder;
    private final PastWhoQuestionPhraseBuilder pastWhoQuestionPhraseBuilder;
    private final FutureWhoQuestionPhraseBuilder futureWhoQuestionPhraseBuilder;
    private final PresentInfinitivePhraseBuilder presentInfinitivePhraseBuilder;
    private final PresentPhraseBuilder presentPhraseBuilder;
    private final PresentAlwaysPhraseBuilder presentAlwaysPhraseBuilder;
    private final FuturePhraseBuilder futurePhraseBuilder;

    public ViewPhrase getPhrase(){

        Verb verb = verbService.getRandomVerb();
        List<VerbPhraseBuilder> verbPhraseBuilders = new ArrayList<>();

        verbPhraseBuilders.add(allTimeLongPhraseBuilder);

        if (verbService.isTimeExist(verb, TIME_PAST)){
            verbPhraseBuilders.add(pastAlreadyPhraseBuilder);
            if (verbService.isUnitExist(verb, GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD, TIME_PAST)){
                verbPhraseBuilders.add(pastWhoQuestionPhraseBuilder);
            }
        }

        if (verbService.isTimeExist(verb, TIME_PRESENT)){
            verbPhraseBuilders.add(presentInfinitivePhraseBuilder);
            verbPhraseBuilders.add(presentPhraseBuilder);
            verbPhraseBuilders.add(presentAlwaysPhraseBuilder);
            if (verbService.isUnitExist(verb, GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD, TIME_PRESENT)){
                verbPhraseBuilders.add(presentWhoQuestionPhraseBuilder);
            }
        }

        if (verbService.isTimeExist(verb, TIME_FUTURE)){
            verbPhraseBuilders.add(futurePhraseBuilder);
            if (verbService.isUnitExist(verb, GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD, TIME_FUTURE)){
                verbPhraseBuilders.add(futureWhoQuestionPhraseBuilder);
            }
        }

        return verbPhraseBuilders.get(RandomService.getRandom(verbPhraseBuilders.size())).getPhrase(verb);
    }

    @Autowired
    public VerbPhraseService(AllTimeLongPhraseBuilder allTimeLongPhraseBuilder, VerbService verbService,
                             PastAlreadyPhraseBuilder pastAlreadyPhraseBuilder,
                             PresentWhoQuestionPhraseBuilder presentWhoQuestionPhraseBuilder,
                             PastWhoQuestionPhraseBuilder pastWhoQuestionPhraseBuilder,
                             FutureWhoQuestionPhraseBuilder futureWhoQuestionPhraseBuilder,
                             PresentInfinitivePhraseBuilder presentInfinitivePhraseBuilder,
                             PresentPhraseBuilder presentPhraseBuilder,
                             PresentAlwaysPhraseBuilder presentAlwaysPhraseBuilder,
                             FuturePhraseBuilder futurePhraseBuilder) {
        this.allTimeLongPhraseBuilder = allTimeLongPhraseBuilder;
        this.verbService = verbService;
        this.pastAlreadyPhraseBuilder = pastAlreadyPhraseBuilder;
        this.presentWhoQuestionPhraseBuilder = presentWhoQuestionPhraseBuilder;
        this.pastWhoQuestionPhraseBuilder = pastWhoQuestionPhraseBuilder;
        this.futureWhoQuestionPhraseBuilder = futureWhoQuestionPhraseBuilder;
        this.presentInfinitivePhraseBuilder = presentInfinitivePhraseBuilder;
        this.presentPhraseBuilder = presentPhraseBuilder;
        this.presentAlwaysPhraseBuilder = presentAlwaysPhraseBuilder;
        this.futurePhraseBuilder = futurePhraseBuilder;
    }

}
