package il.george_nika.phrase2.service.phrase;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.PhraseBuilderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

abstract class AbstractPhraseBuilder implements PhraseBuilder{

    @Autowired
    protected PhraseBuilderService phraseBuilderService;

    protected static LanguageUnit dot = new LanguageUnit(". ", ". ", ". ");
    protected static LanguageUnit comma = new LanguageUnit(", ", ", ", ", ");
    protected static LanguageUnit questionSign = new LanguageUnit("? ", "? ", "? ");
    protected static LanguageUnit whoQuestion = new LanguageUnit("Кто ", "מי ", "ми ");
    protected static LanguageUnit whenQuestion = new LanguageUnit("Когда ", "מתי ", "матай ");
    protected static LanguageUnit always = new LanguageUnit("всегда ", "תמיד ", "тамид ");
    protected static LanguageUnit already = new LanguageUnit("уже ", "כבר ", "квар ");
    protected static LanguageUnit notYet = new LanguageUnit("еще не ", "עוד לא ", "од лё ");
    protected static LanguageUnit today = new LanguageUnit("сегодня ", "היום ", "хайом ");
    protected static LanguageUnit yesterday = new LanguageUnit("вчера ", "אתמול ", "этмоль ");
    protected static LanguageUnit tomorrow = new LanguageUnit("завтра ", "מחר ", "махар ");

    protected ViewPhrase buildPhrase(List<LanguageUnit> languageUnits) {
        ViewPhrase result = new ViewPhrase();
        for (LanguageUnit loopLU : languageUnits) {
            result.addLanguageUnit(loopLU);
        }
        return result;
    }
}
