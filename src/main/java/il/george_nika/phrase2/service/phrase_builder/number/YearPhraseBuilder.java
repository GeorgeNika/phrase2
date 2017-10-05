package il.george_nika.phrase2.service.phrase_builder.number;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.view.ViewPhrase;

import java.util.ArrayList;
import java.util.List;

public class YearPhraseBuilder extends AbstractNumberPhraseBuilder {

    private static LanguageUnit YEAR =   new LanguageUnit("в году","בשנה","бе шана");

    @Override
    public ViewPhrase getPhrase() {
        int russianYear = 0;
        int tempNumber;
        List<LanguageUnit> tempCollection = new ArrayList<>();

        tempCollection.add(YEAR);

        if (randomService.getRandom(1) == 0) {
            russianYear = russianYear + 2000;
            tempCollection.add(numberService.getThousand(2));
            tempNumber = randomService.getRandom(26);
            russianYear = russianYear + tempNumber;
            tempCollection.add(numberService.getDozens(tempNumber));
        } else {
            russianYear = russianYear + 1900;
            tempCollection.add(numberService.getThousand(1));
            tempCollection.add(numberService.getHundred(9));
            tempNumber = randomService.getRandom(100);
            russianYear = russianYear + tempNumber;
            tempCollection.add(numberService.getDozens(tempNumber));
        }

        ViewPhrase result = buildPhrase(tempCollection);
        result.setRussian(YEAR.getRussian() + " " + russianYear);
        return result;
    }
}
