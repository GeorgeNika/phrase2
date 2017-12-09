package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.service.data.PronounService;
import il.george_nika.phrase2.service.data.VerbService;
import il.george_nika.phrase2.service.phrase_builder.AbstractPhraseBuilder;
import org.springframework.beans.factory.annotation.Autowired;

abstract public class AbstractVerbPhraseBuilder extends AbstractPhraseBuilder implements VerbPhraseBuilder {

    protected final PronounService pronounService;
    protected final VerbService verbService;

    static LanguageUnit dot = new LanguageUnit(". ", ". ", ". ");
    static LanguageUnit comma = new LanguageUnit(", ", ", ", ", ");
    static LanguageUnit questionSign = new LanguageUnit("? ", "? ", "? ");
    static LanguageUnit whoQuestion = new LanguageUnit("Кто ", "מי ", "ми ");
    static LanguageUnit whenQuestion = new LanguageUnit("Когда ", "מתי ", "матай ");
    static LanguageUnit always = new LanguageUnit("всегда ", "תמיד ", "тамид ");
    static LanguageUnit already = new LanguageUnit("уже ", "כבר ", "квар ");
    static LanguageUnit notYet = new LanguageUnit("еще не ", "עוד לא ", "од лё ");
    static LanguageUnit today = new LanguageUnit("сегодня ", "היום ", "хайом ");
    static LanguageUnit yesterday = new LanguageUnit("вчера ", "אתמול ", "этмоль ");
    static LanguageUnit tomorrow = new LanguageUnit("завтра ", "מחר ", "махар ");

    @Autowired
    public AbstractVerbPhraseBuilder(PronounService pronounService, VerbService verbService) {
        this.pronounService = pronounService;
        this.verbService = verbService;
    }
}
