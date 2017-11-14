package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.dao.*;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.ActionVerb;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.verb.VerbData;
import il.george_nika.phrase2.model.view.verb.VerbForDetailView;
import il.george_nika.phrase2.service.RandomService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;
import static il.george_nika.phrase2.service.data.TestUtil.getVerbData;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations="classpath:test.properties")
public class TestVerbService {

    @Autowired private VerbRepository verbRepository;
    @Autowired private VerbDataRepository verbDataRepository;
    @Autowired private LanguageUnitRepository languageUnitRepository;
    @Autowired private ActionVerbRepository actionVerbRepository;

    private VerbService verbService;

    @Before
    public void setVerbService(){
        verbService = new VerbService(verbRepository, verbDataRepository,
                languageUnitRepository, actionVerbRepository);
    }

    @Test
    public void getVerbByIdTest(){
        List<Verb> verbsFromDataBase = setDataBase();
        Verb pastVerb = verbsFromDataBase.get(0);
        Verb presentVerb = verbsFromDataBase.get(1);

        Assert.assertEquals((long)verbService.getVerbById(pastVerb.getId()).getId(), (long)pastVerb.getId());
        Assert.assertNotEquals((long)verbService.getVerbById(presentVerb.getId()).getId(),(long)pastVerb.getId());
    }

    @Test
    public void getAllActionVerbIdsTest(){
        List<Verb> verbsFromDataBase = setDataBase();
        Verb pastVerb = verbsFromDataBase.get(0);
        Verb presentVerb = verbsFromDataBase.get(1);
        Verb futureVerb = verbsFromDataBase.get(2);

        Assert.assertEquals(verbService.getAllActionVerbIds().size(), 2);
        Assert.assertTrue(verbService.getAllActionVerbIds().contains(pastVerb.getId()));
        Assert.assertTrue(verbService.getAllActionVerbIds().contains(presentVerb.getId()));
        Assert.assertFalse(verbService.getAllActionVerbIds().contains(futureVerb.getId()));
    }

    @Test
    public void isTimeExistTest(){
        List<Verb> verbsFromDataBase = setDataBase();
        Verb pastVerb = verbService.getVerbById(verbsFromDataBase.get(0).getId());
        Verb presentVerb = verbService.getVerbById(verbsFromDataBase.get(1).getId());
        Verb futureVerb = verbService.getVerbById(verbsFromDataBase.get(2).getId());

        Assert.assertTrue(verbService.isTimeExist(pastVerb, TIME_PAST));
        Assert.assertFalse(verbService.isTimeExist(pastVerb, TIME_PRESENT));
        Assert.assertFalse(verbService.isTimeExist(pastVerb, TIME_FUTURE));
        Assert.assertFalse(verbService.isTimeExist(presentVerb, TIME_PAST));
        Assert.assertTrue(verbService.isTimeExist(presentVerb, TIME_PRESENT));
        Assert.assertFalse(verbService.isTimeExist(presentVerb, TIME_FUTURE));
        Assert.assertFalse(verbService.isTimeExist(futureVerb, TIME_PAST));
        Assert.assertFalse(verbService.isTimeExist(futureVerb, TIME_PRESENT));
        Assert.assertTrue(verbService.isTimeExist(futureVerb, TIME_FUTURE));
    }

    @Test
    public void isUnitExistBySeparateDataTest(){
        List<Verb> verbsFromDataBase = setDataBase();
        Verb futureVerb = verbService.getVerbById(verbsFromDataBase.get(2).getId());

        Assert.assertTrue(verbService.isUnitExist(futureVerb, GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND, TIME_FUTURE));
        Assert.assertTrue(verbService.isUnitExist(futureVerb, GENDER_FEMININE, QUANTITY_PLURAL, PERSON_THIRD, TIME_FUTURE));
        Assert.assertTrue(verbService.isUnitExist(futureVerb, GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_FIRST, TIME_FUTURE));

        Assert.assertFalse(verbService.isUnitExist(futureVerb, GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND, TIME_PAST));
        Assert.assertFalse(verbService.isUnitExist(futureVerb, GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST, TIME_FUTURE));
        Assert.assertFalse(verbService.isUnitExist(futureVerb, GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_SECOND, TIME_FUTURE));
        Assert.assertFalse(verbService.isUnitExist(futureVerb, GENDER_FEMININE, QUANTITY_PLURAL, PERSON_SECOND, TIME_FUTURE));
    }


    @Test
    public void isUnitExistByPronounTest(){
        List<Verb> verbsFromDataBase = setDataBase();
        Verb futureVerb = verbService.getVerbById(verbsFromDataBase.get(2).getId());

        PronounService pronounService = new PronounService(new RandomService(), new PronounRepository());
        Pronoun pronoun;

        pronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND);
        Assert.assertTrue(verbService.isUnitExist(futureVerb, pronoun, TIME_FUTURE));

        pronoun = pronounService.getPronoun(GENDER_FEMININE, QUANTITY_PLURAL, PERSON_THIRD);
        Assert.assertTrue(verbService.isUnitExist(futureVerb, pronoun, TIME_FUTURE));

        pronoun = pronounService.getPronoun(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_FIRST);
        Assert.assertTrue(verbService.isUnitExist(futureVerb, pronoun, TIME_FUTURE));

        pronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND);
        Assert.assertFalse(verbService.isUnitExist(futureVerb, pronoun, TIME_PAST));

        pronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST);
        Assert.assertFalse(verbService.isUnitExist(futureVerb, pronoun, TIME_FUTURE));

        pronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_SECOND);
        Assert.assertFalse(verbService.isUnitExist(futureVerb, pronoun, TIME_FUTURE));

        pronoun = pronounService.getPronoun(GENDER_FEMININE, QUANTITY_PLURAL, PERSON_SECOND);
        Assert.assertFalse(verbService.isUnitExist(futureVerb, pronoun, TIME_FUTURE));
    }

    @Test
    public void getVerbsOnPageTest(){
        List<Verb> verbsFromDataBase = setDataBase();
        Verb pastVerb = verbService.getVerbById(verbsFromDataBase.get(0).getId());
        Verb futureVerb = verbService.getVerbById(verbsFromDataBase.get(2).getId());

        Page<Verb> testPage = verbService.getVerbsPage(0,2, "");
        Assert.assertEquals(testPage.getTotalElements(), 3);
        Assert.assertEquals(testPage.getTotalPages(), 2);
        Assert.assertEquals(testPage.getContent().size(), 2);
        Assert.assertEquals(testPage.getNumber(), 0);
        Assert.assertTrue(testPage.getContent().contains(pastVerb));
        Assert.assertFalse(testPage.getContent().contains(futureVerb));

        testPage = verbService.getVerbsPage(1,2, "");
        Assert.assertEquals(testPage.getTotalElements(), 3);
        Assert.assertEquals(testPage.getTotalPages(), 2);
        Assert.assertEquals(testPage.getContent().size(), 1);
        Assert.assertEquals(testPage.getNumber(), 1);
        Assert.assertTrue(testPage.getContent().contains(futureVerb));
        Assert.assertFalse(testPage.getContent().contains(pastVerb));

        testPage = verbService.getVerbsPage(2,2, "");
        Assert.assertEquals(testPage.getTotalElements(), 3);
        Assert.assertEquals(testPage.getTotalPages(), 2);
        Assert.assertEquals(testPage.getContent().size(), 2);
        Assert.assertEquals(testPage.getNumber(), 0);
        Assert.assertTrue(testPage.getContent().contains(pastVerb));
        Assert.assertFalse(testPage.getContent().contains(futureVerb));

        testPage = verbService.getVerbsPage(1,2, pastVerb.getInfinitive().getHebrew().substring(2,6));
        Assert.assertEquals(testPage.getTotalElements(), 1);
        Assert.assertTrue(testPage.getContent().contains(pastVerb));

        testPage = verbService.getVerbsPage(1,2, pastVerb.getInfinitive().getRussian().substring(2,6));
        Assert.assertEquals(testPage.getTotalElements(), 1);
        Assert.assertTrue(testPage.getContent().contains(pastVerb));

        testPage = verbService.getVerbsPage(1,2, pastVerb.getInfinitive().getTranscription().substring(2,6));
        Assert.assertEquals(testPage.getTotalElements(), 0);
        Assert.assertFalse(testPage.getContent().contains(pastVerb));
    }

    @Test
    public void getRandomVerbTest(){
        List<Verb> verbsFromDataBase = setDataBase();
        Verb pastVerb = verbService.getVerbById(verbsFromDataBase.get(0).getId());
        Verb presentVerb = verbService.getVerbById(verbsFromDataBase.get(1).getId());
        Verb futureVerb = verbService.getVerbById(verbsFromDataBase.get(2).getId());

        for (int i=0; i<10 ; i++) {
            Verb randomVerb = verbService.getRandomVerb();
            Assert.assertTrue((randomVerb.getId() == pastVerb.getId())
                    || (randomVerb.getId() == presentVerb.getId())
                    || (randomVerb.getId() == futureVerb.getId()));
        }
    }

    @Test
    public void getRandomActionVerbTest(){
        List<Verb> verbsFromDataBase = setDataBase();
        Verb pastVerb = verbService.getVerbById(verbsFromDataBase.get(0).getId());
        Verb presentVerb = verbService.getVerbById(verbsFromDataBase.get(1).getId());
        Verb futureVerb = verbService.getVerbById(verbsFromDataBase.get(2).getId());

        for (int i=0; i<30 ; i++) {
            Verb randomActionVerb = verbService.getRandomActionVerb();
            Assert.assertTrue((randomActionVerb.getId() == pastVerb.getId())
                    || (randomActionVerb.getId() == presentVerb.getId()));
            Assert.assertNotEquals(randomActionVerb.getId(), futureVerb.getId());
        }
    }

    @Test
    public void getLanguageUnitTest(){
        List<Verb> verbsFromDataBase = setDataBase();
        Verb futureVerb = verbService.getVerbById(verbsFromDataBase.get(2).getId());

        PronounService pronounService = new PronounService(new RandomService(), new PronounRepository());
        Pronoun pronoun;

        pronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND);
        Assert.assertNotNull(verbService.getLanguageUnitByPronounByTime(futureVerb, pronoun, TIME_FUTURE));

        pronoun = pronounService.getPronoun(GENDER_FEMININE, QUANTITY_PLURAL, PERSON_THIRD);
        Assert.assertNotNull(verbService.getLanguageUnitByPronounByTime(futureVerb, pronoun, TIME_FUTURE));

        pronoun = pronounService.getPronoun(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_FIRST);
        Assert.assertNotNull(verbService.getLanguageUnitByPronounByTime(futureVerb, pronoun, TIME_FUTURE));

        try {
            pronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND);
            verbService.getLanguageUnitByPronounByTime(futureVerb, pronoun, TIME_PAST);
            Assert.fail();
        }catch (Exception ex){}

        try {
            pronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST);
            verbService.getLanguageUnitByPronounByTime(futureVerb, pronoun, TIME_FUTURE);
            Assert.fail();
        }catch (Exception ex){}

        try {
            pronoun = pronounService.getPronoun(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_SECOND);
            verbService.getLanguageUnitByPronounByTime(futureVerb, pronoun, TIME_FUTURE);
            Assert.fail();
        }catch (Exception ex){}

        try{
            pronoun = pronounService.getPronoun(GENDER_FEMININE, QUANTITY_PLURAL, PERSON_SECOND);
            verbService.getLanguageUnitByPronounByTime(futureVerb, pronoun, TIME_FUTURE);
            Assert.fail();
        }catch (Exception ex){}
    }

    @Test
    public void saveVerbByVerbForViewTest(){
        List<Verb> verbsFromDataBase = setDataBase();
        Verb pastVerb = verbService.getVerbById(verbsFromDataBase.get(0).getId());
        Verb futureVerb = verbService.getVerbById(verbsFromDataBase.get(2).getId());

        VerbForDetailView newVerbForDetailView = new VerbForDetailView();
        List<VerbData> newVerbDataCollection = new ArrayList<>();
        newVerbDataCollection.add(getVerbData(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_THIRD, TIME_PRESENT));
        newVerbDataCollection.add(getVerbData(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_SECOND, TIME_PRESENT));
        newVerbForDetailView.setVerbDataCollection(newVerbDataCollection);
        newVerbForDetailView.setInfinitive(new LanguageUnit("новый", "חדש", "new"));
        Assert.assertEquals(verbService.getVerbsPage(0,2,"").getTotalElements(),3);
        verbService.saveVerbByVerbForDetailView(newVerbForDetailView);
        Assert.assertEquals(verbService.getVerbsPage(0,2,"").getTotalElements(),4);

        VerbForDetailView changeInfinitiveInVerbForDetailView = new VerbForDetailView(pastVerb);
        changeInfinitiveInVerbForDetailView.setInfinitive(new LanguageUnit("новый", "חדש", "new"));
        String testString = changeInfinitiveInVerbForDetailView.getInfinitive().getTranscription();
        Assert.assertNotEquals(verbService.getVerbById(pastVerb.getId()).getInfinitive().getTranscription(), testString);
        verbService.saveVerbByVerbForDetailView(changeInfinitiveInVerbForDetailView);
        Assert.assertEquals(verbService.getVerbById(pastVerb.getId()).getInfinitive().getTranscription(), testString);

        VerbForDetailView addVerbDataInVerbForDetailView = new VerbForDetailView(futureVerb);
        Assert.assertEquals(verbService.getVerbById(futureVerb.getId()).getVerbDataCollection().size(), 3);
        List<VerbData> tempCollection = addVerbDataInVerbForDetailView.getVerbDataCollection();
        tempCollection.add(getVerbData(GENDER_FEMININE, QUANTITY_PLURAL, PERSON_SECOND, TIME_PRESENT));
        addVerbDataInVerbForDetailView.setVerbDataCollection(tempCollection);
        verbService.saveVerbByVerbForDetailView(addVerbDataInVerbForDetailView);
        Assert.assertEquals(verbService.getVerbById(futureVerb.getId()).getVerbDataCollection().size(), 4);
    }

    @Test
    public void changeActionVerbTest(){
        List<Verb> verbsFromDataBase = setDataBase();
        Verb pastVerb = verbService.getVerbById(verbsFromDataBase.get(0).getId());
        Verb futureVerb = verbService.getVerbById(verbsFromDataBase.get(2).getId());

        Assert.assertEquals(verbService.getAllActionVerbIds().size(), 2);
        Assert.assertTrue(verbService.getAllActionVerbIds().contains(pastVerb.getId()));
        Assert.assertFalse(verbService.getAllActionVerbIds().contains(futureVerb.getId()));

        verbService.changeActionVerb(pastVerb.getId());
        Assert.assertEquals(verbService.getAllActionVerbIds().size(), 1);
        Assert.assertFalse(verbService.getAllActionVerbIds().contains(pastVerb.getId()));

        verbService.changeActionVerb(futureVerb.getId());
        Assert.assertEquals(verbService.getAllActionVerbIds().size(), 2);
        Assert.assertTrue(verbService.getAllActionVerbIds().contains(futureVerb.getId()));
        Assert.assertFalse(verbService.getAllActionVerbIds().contains(pastVerb.getId()));
    }

    private List<Verb> setDataBase(){
        List<Verb> resultList = new ArrayList<>();

        List<VerbData> pastVerbDataCollection = new ArrayList<>();
        pastVerbDataCollection.add(getVerbData(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST, TIME_PAST));
        Verb pastVerb = new Verb();
        pastVerb.setInfinitive(new LanguageUnit("past_russian", "past_hebrew", "past_transcr"));
        pastVerb.setVerbDataCollection(pastVerbDataCollection);
        resultList.add(verbRepository.save(pastVerb));

        List<VerbData> presentVerbDataCollection = new ArrayList<>();
        presentVerbDataCollection.add(getVerbData(GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_THIRD, TIME_PRESENT));
        presentVerbDataCollection.add(getVerbData(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_SECOND, TIME_PRESENT));
        Verb presentVerb = new Verb();
        presentVerb.setInfinitive(new LanguageUnit("present_russian", "present_hebrew", "present_transcr"));
        presentVerb.setVerbDataCollection(presentVerbDataCollection);
        resultList.add(verbRepository.save(presentVerb));

        List<VerbData> futureVerbDataCollection = new ArrayList<>();
        futureVerbDataCollection.add(getVerbData(GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND, TIME_FUTURE));
        futureVerbDataCollection.add(getVerbData(GENDER_FEMININE, QUANTITY_PLURAL, PERSON_THIRD, TIME_FUTURE));
        futureVerbDataCollection.add(getVerbData(GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_FIRST, TIME_FUTURE));
        Verb futureVerb = new Verb();
        futureVerb.setInfinitive(new LanguageUnit("future_russian", "future_hebrew", "future_transcr"));
        futureVerb.setVerbDataCollection(futureVerbDataCollection);
        resultList.add(verbRepository.save(futureVerb));


        ActionVerb pastActionVerb = new ActionVerb();
        pastActionVerb.setVerb(pastVerb);
        actionVerbRepository.save(pastActionVerb);

        ActionVerb presentActionVerb = new ActionVerb();
        presentActionVerb.setVerb(presentVerb);
        actionVerbRepository.save(presentActionVerb);

        return resultList;
    }
}
