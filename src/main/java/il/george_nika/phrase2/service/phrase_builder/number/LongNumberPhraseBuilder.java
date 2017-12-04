package il.george_nika.phrase2.service.phrase_builder.number;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 05.10.2017.
 */
public class LongNumberPhraseBuilder extends AbstractNumberPhraseBuilder {
    @Override
    public ViewPhrase getPhrase() {

        List<LanguageUnit> tempCollection = new ArrayList<>();
        for (int i = 0 ; i < 9 ; i++){
            tempCollection.add(numberService.getSingleNumber(randomService.getRandom(10)));
        }
        return buildPhrase(tempCollection, new ArrayList<>());
    }
}
