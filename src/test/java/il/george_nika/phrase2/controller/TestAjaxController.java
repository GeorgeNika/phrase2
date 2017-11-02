package il.george_nika.phrase2.controller;

import il.george_nika.phrase2.service.PhraseService;
import il.george_nika.phrase2.service.data.NounService;
import il.george_nika.phrase2.service.data.VerbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAjaxController {

    @Autowired private PhraseService phraseService;
    @Autowired private NounService nounService;
    @Autowired private VerbService verbService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(phraseService).isNotNull();
        assertThat(nounService).isNotNull();
        assertThat(verbService).isNotNull();
    }

    //todo add more tests

}
