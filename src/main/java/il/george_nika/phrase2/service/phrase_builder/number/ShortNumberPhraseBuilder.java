package il.george_nika.phrase2.service.phrase_builder.number;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

public class ShortNumberPhraseBuilder extends AbstractNumberPhraseBuilder {
    @Override
    public ViewPhrase getPhrase() {
        int russianNumber = 0 ;
        int tempNumber;
        int quantityOfNumber = randomService.getRandom(4);
        List<LanguageUnit> tempCollection = new ArrayList<>();

        if (quantityOfNumber >= 3 ){
            tempNumber = randomService.getRandom(10);
            russianNumber = russianNumber + tempNumber*1000;
            tempCollection.add(numberService.getThousand(tempNumber));
        }

        if (quantityOfNumber >= 2){
            tempNumber = randomService.getRandom(10);
            russianNumber = russianNumber + tempNumber*100;
            tempCollection.add(numberService.getHundred(tempNumber));
        }

        if (quantityOfNumber >= 1) {
            tempNumber = randomService.getRandom(100);
        } else {
            tempNumber = randomService.getRandom(10);
        }
        russianNumber = russianNumber + tempNumber;
        tempCollection.add(numberService.getDozens(tempNumber));

        ViewPhrase result = buildPhrase(tempCollection);
        result.setRussian(""+russianNumber);
        return result;
    }
}
