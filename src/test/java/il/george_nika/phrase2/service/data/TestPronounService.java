package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.dao.PronounRepository;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.data.verb.Verb;
import il.george_nika.phrase2.model.data.verb.VerbData;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;
import static il.george_nika.phrase2.service.data.TestUtil.getVerbData;

public class TestPronounService {

    private PronounService pronounService = new PronounService(new PronounRepository());

    @Test
    public void getRandomPronounByVerbTest() {
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
        } catch (RuntimeException ex){}

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

    @Test
    public void getPronounTest() {
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

    private void testPronoun(int gender, int quantity, int person){
        Pronoun testPronoun = pronounService.getPronoun(gender, quantity, person);
        Assert.assertEquals(testPronoun.getGender(), gender);
        Assert.assertEquals(testPronoun.getQuantity(),quantity);
        Assert.assertEquals(testPronoun.getPerson(),person);
    }
}
