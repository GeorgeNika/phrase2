package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.dao.NounRepository;
import il.george_nika.phrase2.service.RandomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestNounService {

    @Autowired private NounRepository nounRepository;
    @Autowired private RandomService randomService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(nounRepository).isNotNull();
        assertThat(randomService).isNotNull();
    }
}
