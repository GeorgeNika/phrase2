package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.dao.PronounRepository;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.verb.VerbData;
import il.george_nika.phrase2.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PronounService {

    private RandomService randomService;
    private PronounRepository pronounRepository;

    @Autowired
    public PronounService(RandomService randomService, PronounRepository pronounRepository){
        this.randomService = randomService;
        this.pronounRepository = pronounRepository;
    }

    public Pronoun getPronounByVerb(Verb verb, Integer time){
        List<Pronoun> tempPronouns = new ArrayList<>();
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
        for(Pronoun loopPronoun : pronounRepository.getAllPronouns()){
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
}
