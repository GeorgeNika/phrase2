package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.dao.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberService {

    @Autowired
    NumberRepository numberRepository;

    public LanguageUnit getDozens(int number){
        if (number == 0) {
            return new LanguageUnit();
        }
        return numberRepository.getDozens(number);
    }

    public LanguageUnit getHundred(int hundred){
        if (hundred == 0) {
            return new LanguageUnit();
        }
        return numberRepository.getHundred(hundred);
    }

    public LanguageUnit getThousand(int thousand){
        if (thousand == 0) {
            return new LanguageUnit();
        }
        return  numberRepository.getThousand(thousand);
    }

    public LanguageUnit getSingleNumber(int number){
        return numberRepository.getSingleNumber(number);
    }
}
