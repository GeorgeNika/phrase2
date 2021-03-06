package il.george_nika.phrase2.service.phrase_builder.verb;

import il.george_nika.phrase2.service.data.PronounService;
import il.george_nika.phrase2.service.data.VerbService;
import org.springframework.stereotype.Component;

import static il.george_nika.phrase2.model.ModelConstants.TIME_FUTURE;

@Component
public class FutureWhoQuestionPhraseBuilder extends AbstractWhoQuestionPhraseBuilder{
    private int time = TIME_FUTURE;

    public FutureWhoQuestionPhraseBuilder(PronounService pronounService, VerbService verbService) {
        super(pronounService, verbService);
    }
}
