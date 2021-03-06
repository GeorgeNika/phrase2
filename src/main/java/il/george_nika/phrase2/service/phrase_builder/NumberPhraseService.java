package il.george_nika.phrase2.service.phrase_builder;

import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.phrase_builder.number.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NumberPhraseService {


    private final ShortNumberPhraseBuilder shortNumberPhraseBuilder;
    private final LongNumberPhraseBuilder longNumberPhraseBuilder;
    private final YearPhraseBuilder yearPhraseBuilder;
    private final FractionalNumberPhraseBuilder fractionalNumberPhraseBuilder;

    @Autowired
    public NumberPhraseService(ShortNumberPhraseBuilder shortNumberPhraseBuilder,
                               LongNumberPhraseBuilder longNumberPhraseBuilder, YearPhraseBuilder yearPhraseBuilder,
                               FractionalNumberPhraseBuilder fractionalNumberPhraseBuilder) {
        this.shortNumberPhraseBuilder = shortNumberPhraseBuilder;
        this.longNumberPhraseBuilder = longNumberPhraseBuilder;
        this.yearPhraseBuilder = yearPhraseBuilder;
        this.fractionalNumberPhraseBuilder=fractionalNumberPhraseBuilder;
    }

    public ViewPhrase getPhrase(){

        List<NumberPhraseBuilder> numberPhraseBuilders = new ArrayList<>();

        numberPhraseBuilders.add(shortNumberPhraseBuilder);
        numberPhraseBuilders.add(longNumberPhraseBuilder);
        numberPhraseBuilders.add(yearPhraseBuilder);

        return numberPhraseBuilders.get(RandomService.getRandom(numberPhraseBuilders.size())).getPhrase();
    }

    public ViewPhrase getFractionalPhrase(){

        return fractionalNumberPhraseBuilder.getPhrase();
    }


}
