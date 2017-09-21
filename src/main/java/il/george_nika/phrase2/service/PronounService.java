package il.george_nika.phrase2.service;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.verb.VerbData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static il.george_nika.phrase2.model.ModelConstants.*;

@Service
public class PronounService {

    private RandomService randomService;

    @Autowired
    public PronounService(RandomService randomService){
        this.randomService = randomService;
    }

    private List<Pronoun> pronouns = new ArrayList<Pronoun>();

    public Pronoun getPronounByVerb(Verb verb, Integer time){
        List<Pronoun> tempPronouns = new ArrayList<Pronoun>();
        for (VerbData loopVerbData : verb.getVerbDataCollection()){
            if (!loopVerbData.getTime().equals(time)) {
                continue;
            }
            tempPronouns.add(getPronoun(loopVerbData.getGender(), loopVerbData.getQuantity(), loopVerbData.getPerson()));
        }
        if (tempPronouns.size()<1){
            throw new RuntimeException("Can't get pronoun for verb id="+verb.getId()+" time:"+time);
        }
        return tempPronouns.get(randomService.getRandom(tempPronouns.size()));
    }

    public Pronoun getPronoun(int gender, int quantity, int person){
        for(Pronoun loopPronoun : pronouns){
            if (loopPronoun.getGender() != gender){
                continue;
            }
            if (loopPronoun.getQuantity() != quantity){
                continue;
            }
            if (loopPronoun.getPerson() != person){
                continue;
            }
            return loopPronoun;
        }
        throw new RuntimeException("Can't get pronoun for gender:" + gender
                + " quantity:" + quantity + " person:" + person);
    }

    {
        pronouns.add(new Pronoun(new LanguageUnit("Я (м)","אני","ани"), GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_FIRST));
        pronouns.add(new Pronoun(new LanguageUnit("Я (ж)","אני","ани"), GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_FIRST));
        pronouns.add(new Pronoun(new LanguageUnit("Ты (м)","אתה","ата"),GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_SECOND));
        pronouns.add(new Pronoun(new LanguageUnit("Ты (ж)","את","ат"), GENDER_FEMININE ,QUANTITY_SINGULAR, PERSON_SECOND));
        pronouns.add(new Pronoun(new LanguageUnit("Он","הוא","ху"), GENDER_MASCULINE, QUANTITY_SINGULAR, PERSON_THIRD));
        pronouns.add(new Pronoun(new LanguageUnit("Она","היא","хи"), GENDER_FEMININE, QUANTITY_SINGULAR, PERSON_THIRD));
        pronouns.add(new Pronoun(new LanguageUnit("Мы","אנחנו","анахну"), GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_FIRST));
        pronouns.add(new Pronoun(new LanguageUnit("Вы (м)","אתם","атем"), GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_SECOND));
        pronouns.add(new Pronoun(new LanguageUnit("Вы (ж)","אתן","атен"), GENDER_FEMININE, QUANTITY_PLURAL, PERSON_SECOND));
        pronouns.add(new Pronoun(new LanguageUnit("Они (м)","הם","хем"), GENDER_MASCULINE, QUANTITY_PLURAL, PERSON_THIRD));
        pronouns.add(new Pronoun(new LanguageUnit("Они (ж)","הן","хен"), GENDER_FEMININE, QUANTITY_PLURAL, PERSON_THIRD));
    }
}
