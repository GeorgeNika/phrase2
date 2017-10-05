package il.george_nika.phrase2.service.phrase_builder.number;

import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.data.NumberService;
import il.george_nika.phrase2.service.phrase_builder.AbstractPhraseBuilder;
import org.springframework.beans.factory.annotation.Autowired;

abstract public class AbstractNumberPhraseBuilder extends AbstractPhraseBuilder implements NumberPhraseBuilder {

    @Autowired
    protected RandomService randomService;

    @Autowired
    protected NumberService numberService;


}
