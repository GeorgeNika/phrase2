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

    private final NumberService numberService;

    @Autowired
    public FractionalNumberPhraseBuilder(NumberService numberService) {
        this.numberService = numberService;
    }

    @Override
    public ViewPhrase getPhrase() {
        int russianNumber = 0 ;
        int tempNumber;
        List<LanguageUnit> resultCollection = new ArrayList<>();

        if (RandomService.getRandom(3) != 0){
            tempNumber = RandomService.getRandom(10);
            russianNumber = russianNumber + tempNumber*100;
            resultCollection.add(numberService.getHundred(tempNumber));
        }
        tempNumber = RandomService.getRandom(100);
        russianNumber = russianNumber + tempNumber;
        resultCollection.add(numberService.getDozens(tempNumber));

        resultCollection.add(comma);
        tempNumber = RandomService.getRandom(100);
        resultCollection.add(numberService.getDozens(tempNumber));

        ViewPhrase result = buildPhrase(resultCollection, new ArrayList<>());
        result.setRussian(""+russianNumber+comma.getRussian()+tempNumber);
        return result;
    }
}
