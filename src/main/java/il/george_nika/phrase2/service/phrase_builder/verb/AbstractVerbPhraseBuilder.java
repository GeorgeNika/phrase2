package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.service.data.PronounService;
import il.george_nika.phrase2.service.data.VerbService;
import il.george_nika.phrase2.service.phrase_builder.AbstractPhraseBuilder;
import org.springframework.beans.factory.annotation.Autowired;

abstract public class AbstractVerbPhraseBuilder extends AbstractPhraseBuilder implements VerbPhraseBuilder {

    protected final PronounService pronounService;
    protected final VerbService verbService;

    protected static final LanguageUnit questionSign = new LanguageUnit("? ", "? ", "? ");
    protected static final LanguageUnit whoQuestion = new LanguageUnit("Кто ", "מי ", "ми ");
    protected static final LanguageUnit whenQuestion = new LanguageUnit("Когда ", "מתי ", "матай ");
    protected static final LanguageUnit always = new LanguageUnit("всегда ", "תמיד ", "тамид ");
    protected static final LanguageUnit already = new LanguageUnit("уже ", "כבר ", "квар ");
    protected static final LanguageUnit notYet = new LanguageUnit("еще не ", "עוד לא ", "од лё ");
    protected static final LanguageUnit today = new LanguageUnit("сегодня ", "היום ", "хайом ");
    protected static final LanguageUnit yesterday = new LanguageUnit("вчера ", "אתמול ", "этмоль ");
    protected static final LanguageUnit tomorrow = new LanguageUnit("завтра ", "מחר ", "махар ");

    @Autowired
    public AbstractVerbPhraseBuilder(PronounService pronounService, VerbService verbService) {
        this.pronounService = pronounService;
        this.verbService = verbService;
    }
}
