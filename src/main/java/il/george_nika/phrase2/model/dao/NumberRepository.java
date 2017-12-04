package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.LanguageUnit;
import org.springframework.stereotype.Repository;

@Repository
public class NumberRepository {

    private static final LanguageUnit ZERO =   new LanguageUnit("0","אפס","эфес");
    private static final LanguageUnit ONE =    new LanguageUnit("1","אחת","ахат");
    private static final LanguageUnit TWO =    new LanguageUnit("2","שתיים","штайм");
    private static final LanguageUnit THREE =  new LanguageUnit("3","שלוש","шалош");
    private static final LanguageUnit FOUR =   new LanguageUnit("4","ארבע","арба");
    private static final LanguageUnit FIVE =   new LanguageUnit("5","חמש","хамеш");
    private static final LanguageUnit SIX =    new LanguageUnit("6","שש","шеш");
    private static final LanguageUnit SEVEN =  new LanguageUnit("7","שבע","шева");
    private static final LanguageUnit EIGHT =  new LanguageUnit("8","שמונה","шмонэ");
    private static final LanguageUnit NINE =   new LanguageUnit("9","תשע","тейша");

    private static final LanguageUnit TEN =       new LanguageUnit("10","עשר","эсер");
    private static final LanguageUnit ELEVEN =    new LanguageUnit("11","אחת-עשרה","ахат-эсре");
    private static final LanguageUnit TWELVE =    new LanguageUnit("12","שתיים-עשרה","штайм-эсре");
    private static final LanguageUnit THIRTEEN =  new LanguageUnit("13","שלוש-עשרה","шлош-эсре");
    private static final LanguageUnit FOURTEEN =  new LanguageUnit("14","ארבע-עשרה","арба-эсре");
    private static final LanguageUnit FIFTEEN =   new LanguageUnit("15","חמש-עשרה","хамеш-эсре");
    private static final LanguageUnit SIXTEEN =   new LanguageUnit("16","שש-עשרה","шеш-эсре");
    private static final LanguageUnit SEVENTEEN = new LanguageUnit("17","שבע-עשרה","шва-эсре");
    private static final LanguageUnit EIGHTEEN =  new LanguageUnit("18","שמונה-עשרה","шмона-эсре");
    private static final LanguageUnit NINETEEN =  new LanguageUnit("19","תשע-עשרה","тша-эсре");

    private static final LanguageUnit TWO_DECADE =    new LanguageUnit("2","עשרים","эсрим");
    private static final LanguageUnit THREE_DECADE =  new LanguageUnit("3","שלושים","шлошим");
    private static final LanguageUnit FOUR_DECADE =   new LanguageUnit("4","ארבעים","арбаим");
    private static final LanguageUnit FIVE_DECADE =   new LanguageUnit("5","חמישים","хамешим");
    private static final LanguageUnit SIX_DECADE =    new LanguageUnit("6","שישים","шешим");
    private static final LanguageUnit SEVEN_DECADE =  new LanguageUnit("7","שבעים","шевим");
    private static final LanguageUnit EIGHT_DECADE =  new LanguageUnit("8","שמונים","шмоним");
    private static final LanguageUnit NINE_DECADE =   new LanguageUnit("9","תשעים","тешим");

    private static final LanguageUnit ONE_HUNDRED =   new LanguageUnit("1","מאה","меа");
    private static final LanguageUnit TWO_HUNDRED =   new LanguageUnit("2","מאתיים","матаим");
    private static final LanguageUnit THREE_HUNDRED = new LanguageUnit("3","שלוש-מאות","шлош-меот");
    private static final LanguageUnit FOUR_HUNDRED =  new LanguageUnit("4","ארבע-מאות","арба-меот");
    private static final LanguageUnit FIVE_HUNDRED =  new LanguageUnit("5","חמש-מאות","хамеш-меот");
    private static final LanguageUnit SIX_HUNDRED =   new LanguageUnit("6","שש-מאות","шеш-меот");
    private static final LanguageUnit SEVEN_HUNDRED = new LanguageUnit("7","שבע-מאות","шва-меот");
    private static final LanguageUnit EIGHT_HUNDRED = new LanguageUnit("8","שמונה-מאות","шмонэ-меот");
    private static final LanguageUnit NINE_HUNDRED =  new LanguageUnit("9","תשע-מאות","тша-меот");

    private static final LanguageUnit ONE_THOUSAND =   new LanguageUnit("1","אלף","элеф");
    private static final LanguageUnit TWO_THOUSAND =   new LanguageUnit("2","אלפיים","альпаим");
    private static final LanguageUnit THREE_THOUSAND = new LanguageUnit("3","שלושת אלפים","шлошет алафим");
    private static final LanguageUnit FOUR_THOUSAND =  new LanguageUnit("4","ארבעת אלפים","арбаат алафим");
    private static final LanguageUnit FIVE_THOUSAND =  new LanguageUnit("5","חמשת אלפים","хамешат алафим");
    private static final LanguageUnit SIX_THOUSAND =   new LanguageUnit("6","ששת אלפים","шешат алафим");
    private static final LanguageUnit SEVEN_THOUSAND = new LanguageUnit("7","שבעת אלפים","шват алафим");
    private static final LanguageUnit EIGHT_THOUSAND = new LanguageUnit("8","שמונת אלפים","шмонат алафим");
    private static final LanguageUnit NINE_THOUSAND =  new LanguageUnit("9","תשעת אלפים","тшат алафим");

    private static final LanguageUnit ADD = new LanguageUnit("","ו","ве");
    private static final LanguageUnit SPACE = new LanguageUnit(" "," "," ");

    public LanguageUnit getDozens (int number){
        if (number < 10 ){
            return getSingleNumber(number);
        }
        if (number < 20){
            return getSecondTen(number);
        }

        int decade = number / 10;
        LanguageUnit result = getDecade(decade);
        if (decade * 10  != number){
            result.concatLanguageUnit(SPACE);
            result.concatLanguageUnit(ADD);
            result.concatLanguageUnit(SPACE);
            result.concatLanguageUnit(getSingleNumber(number - decade*10));
        }
        return result;
    }

    public LanguageUnit getDecade(int decade){
        if (decade == 2){
            return new LanguageUnit(TWO_DECADE);
        }
        if (decade == 3){
            return new LanguageUnit(THREE_DECADE);
        }
        if (decade == 4){
            return new LanguageUnit(FOUR_DECADE);
        }
        if (decade == 5){
            return new LanguageUnit(FIVE_DECADE);
        }
        if (decade == 6){
            return new LanguageUnit(SIX_DECADE);
        }
        if (decade == 7){
            return new LanguageUnit(SEVEN_DECADE);
        }
        if (decade == 8){
            return new LanguageUnit(EIGHT_DECADE);
        }
        if (decade == 9){
            return new LanguageUnit(NINE_DECADE);
        }
        throw new RuntimeException("wrong parameter for decade - "+decade);
    }
    public LanguageUnit getHundred(int hundred){
        if (hundred == 1){
            return new LanguageUnit(ONE_HUNDRED);
        }
        if (hundred == 2){
            return new LanguageUnit(TWO_HUNDRED);
        }
        if (hundred == 3){
            return new LanguageUnit(THREE_HUNDRED);
        }
        if (hundred == 4){
            return new LanguageUnit(FOUR_HUNDRED);
        }
        if (hundred == 5){
            return new LanguageUnit(FIVE_HUNDRED);
        }
        if (hundred == 6){
            return new LanguageUnit(SIX_HUNDRED);
        }
        if (hundred == 7){
            return new LanguageUnit(SEVEN_HUNDRED);
        }
        if (hundred == 8){
            return new LanguageUnit(EIGHT_HUNDRED);
        }
        if (hundred == 9){
            return new LanguageUnit(NINE_HUNDRED);
        }
        throw new RuntimeException("wrong parameter for hundred - "+hundred);
    }

    public LanguageUnit getThousand(int thousand){
        if (thousand == 1){
            return new LanguageUnit(ONE_THOUSAND);
        }
        if (thousand == 2){
            return new LanguageUnit(TWO_THOUSAND);
        }
        if (thousand == 3){
            return new LanguageUnit(THREE_THOUSAND);
        }
        if (thousand == 4){
            return new LanguageUnit(FOUR_THOUSAND);
        }
        if (thousand == 5){
            return new LanguageUnit(FIVE_THOUSAND);
        }
        if (thousand == 6){
            return new LanguageUnit(SIX_THOUSAND);
        }
        if (thousand == 7){
            return new LanguageUnit(SEVEN_THOUSAND);
        }
        if (thousand == 8){
            return new LanguageUnit(EIGHT_THOUSAND);
        }
        if (thousand == 9){
            return new LanguageUnit(NINE_THOUSAND);
        }
        throw new RuntimeException("wrong parameter for thousand - "+thousand);
    }

    public LanguageUnit getSingleNumber(int number){
        if (number == 0){
            return new LanguageUnit(ZERO);
        }
        if (number == 1){
            return new LanguageUnit(ONE);
        }
        if (number == 2){
            return new LanguageUnit(TWO);
        }
        if (number == 3){
            return new LanguageUnit(THREE);
        }
        if (number == 4){
            return new LanguageUnit(FOUR);
        }
        if (number == 5){
            return new LanguageUnit(FIVE);
        }
        if (number == 6){
            return new LanguageUnit(SIX);
        }
        if (number == 7){
            return new LanguageUnit(SEVEN);
        }
        if (number == 8){
            return new LanguageUnit(EIGHT);
        }
        if (number == 9){
            return new LanguageUnit(NINE);
        }
        throw new RuntimeException("wrong parameter for getSingleNumber - "+number);
    }

    private LanguageUnit getSecondTen(int number){
        if (number == 10){
            return new LanguageUnit(TEN);
        }
        if (number == 11){
            return new LanguageUnit(ELEVEN);
        }
        if (number == 12){
            return new LanguageUnit(TWELVE);
        }
        if (number == 13){
            return new LanguageUnit(THIRTEEN);
        }
        if (number == 14){
            return new LanguageUnit(FOURTEEN);
        }
        if (number == 15){
            return new LanguageUnit(FIFTEEN);
        }
        if (number == 16){
            return new LanguageUnit(SIXTEEN);
        }
        if (number == 17){
            return new LanguageUnit(SEVENTEEN);
        }
        if (number == 18){
            return new LanguageUnit(EIGHTEEN);
        }
        if (number == 19){
            return new LanguageUnit(NINETEEN);
        }
        throw new RuntimeException("wrong parameter for getSecondTen - "+number);
    }



}
