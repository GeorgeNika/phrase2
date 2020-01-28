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
public class ShortNumberPhraseBuilder extends AbstractPhraseBuilder implements NumberPhraseBuilder {

    private final NumberService numberService;

    @Autowired
    public ShortNumberPhraseBuilder( NumberService numberService) {
        this.numberService = numberService;
    }

    @Override
    public ViewPhrase getPhrase() {
        int russianNumber = 0 ;
        int tempNumber;
        int quantityOfDigits = RandomService.getRandom(4);
        List<LanguageUnit> resultCollection = new ArrayList<>();

        if (quantityOfDigits >= 3 ){
            tempNumber = RandomService.getRandom(10);
            russianNumber = russianNumber + tempNumber*1000;
            resultCollection.add(numberService.getThousand(tempNumber));
        }

        if (quantityOfDigits >= 2){
            tempNumber = RandomService.getRandom(10);
            russianNumber = russianNumber + tempNumber*100;
            resultCollection.add(numberService.getHundred(tempNumber));
        }

        if (quantityOfDigits >= 1) {
            tempNumber = RandomService.getRandom(100);
        } else {
            tempNumber = RandomService.getRandom(10);
        }
        russianNumber = russianNumber + tempNumber;
        resultCollection.add(numberService.getDozens(tempNumber));

        ViewPhrase result = buildPhrase(resultCollection, new ArrayList<>());
        result.setRussian(""+russianNumber);
        return result;
    }
}
