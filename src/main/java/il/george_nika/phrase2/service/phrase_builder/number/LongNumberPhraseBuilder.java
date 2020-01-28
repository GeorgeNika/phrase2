package il.george_nika.phrase2.service.phrase_builder.number;

import il.george_nika.phrase2.model.data.LanguageUnit;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.data.NumberService;
import il.george_nika.phrase2.service.phrase_builder.AbstractPhraseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LongNumberPhraseBuilder extends AbstractPhraseBuilder implements NumberPhraseBuilder {

    private final NumberService numberService;

    @Autowired
    public LongNumberPhraseBuilder(NumberService numberService) {
        this.numberService = numberService;
    }

    @Override
    public ViewPhrase getPhrase() {

        List<LanguageUnit> resultCollection = new ArrayList<>();
        for (int i = 0 ; i < 9 ; i++){
            resultCollection.add(numberService.getSingleNumber(RandomService.getRandom(10)));
        }
        return buildPhrase(resultCollection, new ArrayList<>());
    }
}
