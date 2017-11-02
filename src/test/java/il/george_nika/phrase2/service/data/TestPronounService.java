package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.ModelConstants;
import il.george_nika.phrase2.model.dao.PronounRepository;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.verb.VerbData;
import il.george_nika.phrase2.service.RandomService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPronounService {

    @Autowired private RandomService randomService;
    @Autowired private PronounRepository pronounRepository;
    @Autowired private PronounService pronounService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(randomService).isNotNull();
        assertThat(pronounRepository).isNotNull();
        assertThat(pronounService).isNotNull();
    }

    @Test
    public void getRandomPronounByVerbTest() throws Exception {
        Verb tempVerb = getVerb();
        final Pronoun pastPronoun = pronounService.getRandomPronounByVerb(tempVerb,TIME_PAST);
        Assert.assertEquals(tempVerb.getVerbDataCollection().stream()
                .filter(verbData -> verbData.getTime() == TIME_PAST)
                .filter(verbData -> verbData.getGender() == pastPronoun.getGender())
                .filter(verbData -> verbData.getQuantity() == pastPronoun.getQuantity())
                .filter(verbData -> verbData.getPerson() == pastPronoun.getPerson())
                .count() , 1);

        try{
            pronounService.getRandomPronounByVerb(tempVerb,TIME_PRESENT);
            Assert.fail();
        } catch (RuntimeException ex){

        }

        final Pronoun futurePronoun = pronounService.getRandomPronounByVerb(tempVerb,TIME_FUTURE);
        Assert.assertEquals(tempVerb.getVerbDataCollection().stream()
                .filter(verbData -> verbData.getTime() == TIME_FUTURE)
                .filter(verbData -> verbData.getGender() == futurePronoun.getGender())
                .filter(verbData -> verbData.getQuantity() == futurePronoun.getQuantity())
                .filter(verbData -> verbData.getPerson() == futurePronoun.getPerson())
                .count() , 1);



    }

    private Verb getVerb(){
        List<VerbData> tempVerbDataCollection = new ArrayList<>();

        tempVerbDataCollection.add(getVerbData(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST, TIME_PAST));
        tempVerbDataCollection.add(getVerbData(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_THIRD, TIME_PAST));
        tempVerbDataCollection.add(getVerbData(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_SECOND, TIME_PAST));
        tempVerbDataCollection.add(getVerbData(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND, TIME_FUTURE));
        tempVerbDataCollection.add(getVerbData(GENDER_FEMININE, QUANTITY_PLURAL, PERSON_SECOND, TIME_FUTURE));
        tempVerbDataCollection.add(getVerbData(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_FIRST, TIME_FUTURE));
        tempVerbDataCollection.add(getVerbData(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_THIRD, TIME_FUTURE));
        Verb result = new Verb();
        result.setVerbDataCollection(tempVerbDataCollection);
        return  result;
    }

    private VerbData getVerbData(int gender, int quantity, int person, int time){
        VerbData result = new VerbData();
        result.setGender(gender);
        result.setQuantity(quantity);
        result.setPerson(person);
        result.setTime(time);
        return result;
    }

    @Test
    public void getPronounTest() throws Exception {
        testPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST);
        testPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND);
        testPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD);

        testPronoun(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_FIRST);
        testPronoun(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_SECOND);
        testPronoun(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_THIRD);

        testPronoun(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_FIRST);
        testPronoun(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_SECOND);
        testPronoun(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_THIRD);

        testPronoun(GENDER_FEMININE, QUANTITY_PLURAL, PERSON_SECOND);
        testPronoun(GENDER_FEMININE, QUANTITY_PLURAL, PERSON_THIRD);
    }

    private void testPronoun(int gender, int quantity, int person) throws Exception {
        Pronoun testPronoun = pronounService.getPronoun(gender, quantity, person);
        Assert.assertEquals(testPronoun.getGender(), gender);
        Assert.assertEquals(testPronoun.getQuantity(),quantity);
        Assert.assertEquals(testPronoun.getPerson(),person);
    }
}
