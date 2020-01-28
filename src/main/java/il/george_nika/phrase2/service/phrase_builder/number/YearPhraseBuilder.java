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
public class YearPhraseBuilder extends AbstractPhraseBuilder implements NumberPhraseBuilder {

    private final static LanguageUnit YEAR =   new LanguageUnit("в году","בשנה","бе шана");

    private final NumberService numberService;

    @Autowired
    public YearPhraseBuilder(NumberService numberService) {
        this.numberService = numberService;
    }

    @Override
    public ViewPhrase getPhrase() {
        int russianYear = 0;
        int tempNumber;
        List<LanguageUnit> resultCollection = new ArrayList<>();

        resultCollection.add(YEAR);

        if (RandomService.getRandom(2) == 0) {
            russianYear = russianYear + 2000;
            resultCollection.add(numberService.getThousand(2));
            tempNumber = RandomService.getRandom(26);
            russianYear = russianYear + tempNumber;
            resultCollection.add(numberService.getDozens(tempNumber));
        } else {
            russianYear = russianYear + 1900;
            resultCollection.add(numberService.getThousand(1));
            resultCollection.add(numberService.getHundred(9));
            tempNumber = RandomService.getRandom(100);
            russianYear = russianYear + tempNumber;
            resultCollection.add(numberService.getDozens(tempNumber));
        }

        ViewPhrase result = buildPhrase(resultCollection, new ArrayList<>());
        result.setRussian(YEAR.getRussian() + " " + russianYear);
        return result;
    }
}
