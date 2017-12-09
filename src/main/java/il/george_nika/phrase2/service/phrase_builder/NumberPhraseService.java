package il.george_nika.phrase2.service.phrase_builder;

import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.phrase_builder.number.LongNumberPhraseBuilder;
import il.george_nika.phrase2.service.phrase_builder.number.NumberPhraseBuilder;
import il.george_nika.phrase2.service.phrase_builder.number.ShortNumberPhraseBuilder;
import il.george_nika.phrase2.service.phrase_builder.number.YearPhraseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NumberPhraseService {

    private final RandomService randomService;

    private final ShortNumberPhraseBuilder shortNumberPhraseBuilder;
    private final LongNumberPhraseBuilder longNumberPhraseBuilder;
    private final YearPhraseBuilder yearPhraseBuilder;

    @Autowired
    public NumberPhraseService(RandomService randomService, ShortNumberPhraseBuilder shortNumberPhraseBuilder,
                               LongNumberPhraseBuilder longNumberPhraseBuilder, YearPhraseBuilder yearPhraseBuilder) {
        this.randomService = randomService;
        this.shortNumberPhraseBuilder = shortNumberPhraseBuilder;
        this.longNumberPhraseBuilder = longNumberPhraseBuilder;
        this.yearPhraseBuilder = yearPhraseBuilder;
    }

    public ViewPhrase getPhrase(){

        List<NumberPhraseBuilder> numberPhraseBuilders = new ArrayList<>();

        numberPhraseBuilders.add(shortNumberPhraseBuilder);
        numberPhraseBuilders.add(longNumberPhraseBuilder);
        numberPhraseBuilders.add(yearPhraseBuilder);

        return numberPhraseBuilders.get(randomService.getRandom(numberPhraseBuilders.size())).getPhrase();
    }


}
