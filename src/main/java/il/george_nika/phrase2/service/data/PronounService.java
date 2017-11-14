package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.dao.PronounRepository;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PronounService {

    private RandomService randomService;
    private PronounRepository pronounRepository;

    @Autowired
    public PronounService(RandomService randomService, PronounRepository pronounRepository){
        this.randomService = randomService;
        this.pronounRepository = pronounRepository;
    }

    public Pronoun getRandomPronounByVerb(Verb verb, Integer time){

        List<Pronoun> tempPronouns = verb.getVerbDataCollection().stream()
                .filter( verbData -> verbData.getTime() == time )
                .map( verbData -> getPronoun(verbData.getGender(), verbData.getQuantity(), verbData.getPerson()) )
                .collect( Collectors.toList() );

        if (tempPronouns.size()<1){
            throw new RuntimeException("Can't get pronoun for verb id="+verb.getId()+" time:"+time);
        }
        return tempPronouns.get(randomService.getRandom(tempPronouns.size()));
    }

    public Pronoun getPronoun(int gender, int quantity, int person){
        Optional<Pronoun> result = pronounRepository.getAllPronouns().stream()
                .filter( pronoun -> pronoun.getGender() == gender )
                .filter( pronoun -> pronoun.getQuantity() == quantity )
                .filter( pronoun -> pronoun.getPerson() == person )
                .findFirst();
        if ( result.isPresent() ){
            return result.get();
        }
        throw new RuntimeException("Can't get pronoun for gender:" + gender
                + " quantity:" + quantity + " person:" + person);
    }
}
