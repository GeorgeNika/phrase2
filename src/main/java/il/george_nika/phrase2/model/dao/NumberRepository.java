package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.LanguageUnit;
import org.springframework.stereotype.Repository;

@Repository
public class NumberRepository {

    private static LanguageUnit ZERO =   new LanguageUnit("0","אפס","эфес");
    private static LanguageUnit ONE =    new LanguageUnit("1","אחת","ахат");
    private static LanguageUnit TWO =    new LanguageUnit("2","שתיים","штайм");
    private static LanguageUnit THREE =  new LanguageUnit("3","שלוש","шалош");
    private static LanguageUnit FOUR =   new LanguageUnit("4","ארבע","арба");
    private static LanguageUnit FIVE =   new LanguageUnit("5","חמש","хамеш");
    private static LanguageUnit SIX =    new LanguageUnit("6","שש","шеш");
    private static LanguageUnit SEVEN =  new LanguageUnit("7","שבע","шева");
    private static LanguageUnit EIGHT =  new LanguageUnit("8","שמונה","шмонэ");
    private static LanguageUnit NINE =   new LanguageUnit("9","תשע","тейша");

    private static LanguageUnit TEN =       new LanguageUnit("10","עשר","эсер");
    private static LanguageUnit ELEVEN =    new LanguageUnit("11","אחת-עשרה","ахат-эсре");
    private static LanguageUnit TWELVE =    new LanguageUnit("12","שתיים-עשרה","штайм-эсре");
    private static LanguageUnit THIRTEEN =  new LanguageUnit("13","שלוש-עשרה","шлош-эсре");
    private static LanguageUnit FOURTEEN =  new LanguageUnit("14","ארבע-עשרה","арба-эсре");
    private static LanguageUnit FIFTEEN =   new LanguageUnit("15","חמש-עשרה","хамеш-эсре");
    private static LanguageUnit SIXTEEN =   new LanguageUnit("16","שש-עשרה","шеш-эсре");
    private static LanguageUnit SEVENTEEN = new LanguageUnit("17","שבע-עשרה","шва-эсре");
    private static LanguageUnit EIGHTEEN =  new LanguageUnit("18","שמונה-עשרה","шмона-эсре");
    private static LanguageUnit NINETEEN =  new LanguageUnit("19","תשע-עשרה","тша-эсре");

    private static LanguageUnit TWO_DECADE =    new LanguageUnit("2","עשרים","эсрим");
    private static LanguageUnit THREE_DECADE =  new LanguageUnit("3","שלושים","шлошим");
    private static LanguageUnit FOUR_DECADE =   new LanguageUnit("4","ארבעים","арбаим");
    private static LanguageUnit FIVE_DECADE =   new LanguageUnit("5","חמישים","шамешим");
    private static LanguageUnit SIX_DECADE =    new LanguageUnit("6","שישים","шешим");
    private static LanguageUnit SEVEN_DECADE =  new LanguageUnit("7","שבעים","шевим");
    private static LanguageUnit EIGHT_DECADE =  new LanguageUnit("8","שמונים","шмоним");
    private static LanguageUnit NINE_DECADE =   new LanguageUnit("9","תשעים","тешим");

    private static LanguageUnit ONE_HUNDRED =   new LanguageUnit("1","מאה","меа");
    private static LanguageUnit TWO_HUNDRED =   new LanguageUnit("2","מאתיים","матаим");
    private static LanguageUnit THREE_HUNDRED = new LanguageUnit("3","שלוש-מאות","шлош-меот");
    private static LanguageUnit FOUR_HUNDRED =  new LanguageUnit("4","ארבע-מאות","арба-меот");
    private static LanguageUnit FIVE_HUNDRED =  new LanguageUnit("5","חמש-מאות","хамеш-меот");
    private static LanguageUnit SIX_HUNDRED =   new LanguageUnit("6","שש-מאות","шеш-меот");
    private static LanguageUnit SEVEN_HUNDRED = new LanguageUnit("7","שבע-מאות","шва-меот");
    private static LanguageUnit EIGHT_HUNDRED = new LanguageUnit("8","שמונה-מאות","шмонэ-меот");
    private static LanguageUnit NINE_HUNDRED =  new LanguageUnit("9","תשע-מאות","тша-меот");

    private static LanguageUnit ONE_THOUSAND =   new LanguageUnit("1","אלף","элеф");
    private static LanguageUnit TWO_THOUSAND =   new LanguageUnit("2","אלפיים","альпаим");
    private static LanguageUnit THREE_THOUSAND = new LanguageUnit("3","שלושת אלפים","шлошет алафим");
    private static LanguageUnit FOUR_THOUSAND =  new LanguageUnit("4","ארבעת אלפים","арбаат алафим");
    private static LanguageUnit FIVE_THOUSAND =  new LanguageUnit("5","חמשת אלפים","хамешат алафим");
    private static LanguageUnit SIX_THOUSAND =   new LanguageUnit("6","ששת אלפים","шешат алафим");
    private static LanguageUnit SEVEN_THOUSAND = new LanguageUnit("7","שבעת אלפים","шват алафим");
    private static LanguageUnit EIGHT_THOUSAND = new LanguageUnit("8","שמונת אלפים","шмонат алафим");
    private static LanguageUnit NINE_THOUSAND =  new LanguageUnit("9","תשעת אלפים","тшат алафим");

    private static LanguageUnit ADD = new LanguageUnit("","ו","ве");
    private static LanguageUnit SPACE = new LanguageUnit(" "," "," ");

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
            return TWO_DECADE;
        }
        if (decade == 3){
            return THREE_DECADE;
        }
        if (decade == 4){
            return FOUR_DECADE;
        }
        if (decade == 5){
            return FIVE_DECADE;
        }
        if (decade == 6){
            return SIX_DECADE;
        }
        if (decade == 7){
            return SEVEN_DECADE;
        }
        if (decade == 8){
            return EIGHT_DECADE;
        }
        if (decade == 9){
            return NINE_DECADE;
        }
        throw new RuntimeException("wrong parameter for decade - "+decade);
    }
    public LanguageUnit getHundred(int hundred){
        if (hundred == 1){
            return ONE_HUNDRED;
        }
        if (hundred == 2){
            return TWO_HUNDRED;
        }
        if (hundred == 3){
            return THREE_HUNDRED;
        }
        if (hundred == 4){
            return FOUR_HUNDRED;
        }
        if (hundred == 5){
            return FIVE_HUNDRED;
        }
        if (hundred == 6){
            return SIX_HUNDRED;
        }
        if (hundred == 7){
            return SEVEN_HUNDRED;
        }
        if (hundred == 8){
            return EIGHT_HUNDRED;
        }
        if (hundred == 9){
            return NINE_HUNDRED;
        }
        throw new RuntimeException("wrong parameter for hundred - "+hundred);
    }

    public LanguageUnit getThousand(int thousand){
        if (thousand == 1){
            return ONE_THOUSAND;
        }
        if (thousand == 2){
            return TWO_THOUSAND;
        }
        if (thousand == 3){
            return THREE_THOUSAND;
        }
        if (thousand == 4){
            return FOUR_THOUSAND;
        }
        if (thousand == 5){
            return FIVE_THOUSAND;
        }
        if (thousand == 6){
            return SIX_THOUSAND;
        }
        if (thousand == 7){
            return SEVEN_THOUSAND;
        }
        if (thousand == 8){
            return EIGHT_THOUSAND;
        }
        if (thousand == 9){
            return NINE_THOUSAND;
        }
        throw new RuntimeException("wrong parameter for thousand - "+thousand);
    }

    public LanguageUnit getSingleNumber(int number){
        if (number == 0){
            return ZERO;
        }
        if (number == 1){
            return ONE;
        }
        if (number == 2){
            return TWO;
        }
        if (number == 3){
            return THREE;
        }
        if (number == 4){
            return FOUR;
        }
        if (number == 5){
            return FIVE;
        }
        if (number == 6){
            return SIX;
        }
        if (number == 7){
            return SEVEN;
        }
        if (number == 8){
            return EIGHT;
        }
        if (number == 9){
            return NINE;
        }
        throw new RuntimeException("wrong parameter for getSingleNumber - "+number);
    }

    private LanguageUnit getSecondTen(int number){
        if (number == 10){
            return TEN;
        }
        if (number == 11){
            return ELEVEN;
        }
        if (number == 12){
            return TWELVE;
        }
        if (number == 13){
            return THIRTEEN;
        }
        if (number == 14){
            return FOURTEEN;
        }
        if (number == 15){
            return FIFTEEN;
        }
        if (number == 16){
            return SIXTEEN;
        }
        if (number == 17){
            return SEVENTEEN;
        }
        if (number == 18){
            return EIGHTEEN;
        }
        if (number == 19){
            return NINETEEN;
        }
        throw new RuntimeException("wrong parameter for getSecondTen - "+number);
    }



}
