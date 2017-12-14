package il.george_nika.phrase2.service.phrase_builder.number;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.data.NumberService;
import il.george_nika.phrase2.service.phrase_builder.AbstractPhraseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FractionalNumberPhraseBuilder extends AbstractPhraseBuilder implements NumberPhraseBuilder {

    private final RandomService randomService;
    private final NumberService numberService;

    @Autowired
    public FractionalNumberPhraseBuilder(RandomService randomService, NumberService numberService) {
        this.randomService = randomService;
        this.numberService = numberService;
    }

    @Override
    public ViewPhrase getPhrase() {
        int russianNumber = 0 ;
        int tempNumber;
        List<LanguageUnit> resultCollection = new ArrayList<>();

        if (randomService.getRandom(3) != 0){
            tempNumber = randomService.getRandom(10);
            russianNumber = russianNumber + tempNumber*100;
            resultCollection.add(numberService.getHundred(tempNumber));
        }
        tempNumber = randomService.getRandom(100);
        russianNumber = russianNumber + tempNumber;
        resultCollection.add(numberService.getDozens(tempNumber));

        resultCollection.add(comma);
        tempNumber = randomService.getRandom(100);
        resultCollection.add(numberService.getDozens(tempNumber));

        ViewPhrase result = buildPhrase(resultCollection, new ArrayList<>());
        result.setRussian(""+russianNumber+comma.getRussian()+tempNumber);
        return result;
    }
}
