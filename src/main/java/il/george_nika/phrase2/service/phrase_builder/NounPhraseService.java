package il.george_nika.phrase2.service.phrase_builder;

import il.george_nika.phrase2.model.view.ViewPhrase;
import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.phrase_builder.noun.NounWithAdjectivePhraseBuilder;
import il.george_nika.phrase2.service.phrase_builder.number.LongNumberPhraseBuilder;
import il.george_nika.phrase2.service.phrase_builder.number.NumberPhraseBuilder;
import il.george_nika.phrase2.service.phrase_builder.number.ShortNumberPhraseBuilder;
import il.george_nika.phrase2.service.phrase_builder.number.YearPhraseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NounPhraseService extends AbstractPhraseBuilder {

    @Autowired
    private RandomService randomService;

    private @Autowired
    AutowireCapableBeanFactory beanFactory;

    public ViewPhrase getSimplePhrase(){

        NounWithAdjectivePhraseBuilder nounWithAdjectivePhraseBuilder = new NounWithAdjectivePhraseBuilder();


        beanFactory.autowireBean(nounWithAdjectivePhraseBuilder);

        return nounWithAdjectivePhraseBuilder.getPhrase();
    }


}
