package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.data.LanguageUnit;
import il.george_nika.phrase2.model.dao.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberService {

    private final NumberRepository numberRepository;

    private static final LanguageUnit EMPTY_LANGUAGE_UNIT = new LanguageUnit("","" ,"");

    @Autowired
    public NumberService(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    public LanguageUnit getDozens(int number){
        if (number == 0) {
            return EMPTY_LANGUAGE_UNIT;
        }
        return numberRepository.getDozens(number);
    }

    public LanguageUnit getHundred(int hundred){
        if (hundred == 0) {
            return EMPTY_LANGUAGE_UNIT;
        }
        return numberRepository.getHundred(hundred);
    }

    public LanguageUnit getThousand(int thousand){
        if (thousand == 0) {
            return EMPTY_LANGUAGE_UNIT;
        }
        return  numberRepository.getThousand(thousand);
    }

    public LanguageUnit getSingleNumber(int number){
        return numberRepository.getSingleNumber(number);
    }
}
