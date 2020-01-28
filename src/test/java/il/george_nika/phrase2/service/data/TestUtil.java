package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.data.LanguageUnit;
import il.george_nika.phrase2.model.data.verb.VerbData;

/**
 * Created by George on 03.11.2017.
 */
public class TestUtil {

    public static VerbData getVerbData(int gender, int quantity, int person, int time){
        VerbData result = new VerbData();
        result.setGender(gender);
        result.setQuantity(quantity);
        result.setPerson(person);
        result.setTime(time);
        result.setLanguageUnit(new LanguageUnit("пол "+gender, "מספר "+quantity, "tr "+person));
        return result;
    }
}
