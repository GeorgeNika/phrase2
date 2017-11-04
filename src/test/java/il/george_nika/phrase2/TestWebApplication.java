package il.george_nika.phrase2;

import il.george_nika.phrase2.model.dao.*;
import il.george_nika.phrase2.service.PhraseService;
import il.george_nika.phrase2.service.RandomService;
import il.george_nika.phrase2.service.data.NounService;
import il.george_nika.phrase2.service.data.NumberService;
import il.george_nika.phrase2.service.data.PronounService;
import il.george_nika.phrase2.service.data.VerbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWebApplication {

    @Autowired private ActionVerbRepository actionVerbRepository;
    @Autowired private LanguageUnitRepository languageUnitRepository;
    @Autowired private NounRepository nounRepository;
    @Autowired private NumberRepository numberRepository;
    @Autowired private PronounRepository pronounRepository;
    @Autowired private VerbDataRepository verbDataRepository;
    @Autowired private VerbRepository verbRepository;

    @Autowired private NounService nounService;
    @Autowired private NumberService numberService;
    @Autowired private PronounService pronounService;
    @Autowired private VerbService verbService;

    @Autowired private PhraseService phraseService;
    @Autowired private RandomService randomService;

    @Test
    public void contextLoads() throws Exception {

        assertThat(actionVerbRepository).isNotNull();
        assertThat(languageUnitRepository).isNotNull();
        assertThat(nounRepository).isNotNull();
        assertThat(numberRepository).isNotNull();
        assertThat(pronounRepository).isNotNull();
        assertThat(verbDataRepository).isNotNull();
        assertThat(verbRepository).isNotNull();

        assertThat(nounService).isNotNull();
        assertThat(numberService).isNotNull();
        assertThat(pronounService).isNotNull();
        assertThat(verbService).isNotNull();

        assertThat(phraseService).isNotNull();
        assertThat(randomService).isNotNull();
    }


}
