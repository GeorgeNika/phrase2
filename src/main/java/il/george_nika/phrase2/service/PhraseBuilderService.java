package il.george_nika.phrase2.service;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.Verb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhraseBuilderService {

    @Autowired
    VerbService verbService;

    @Autowired
    PronounService pronounService;

    public Verb getRandomActionVerb(){
        return verbService.getRandomActionVerb();
    }

    public Pronoun getPronounByVerb(Verb verb, Integer time){
        return pronounService.getPronounByVerb(verb, time);
    }

    public Pronoun getPronoun (Integer gender, Integer quantity, Integer person){
        return pronounService.getPronoun(gender, quantity, person);
    }

    public LanguageUnit getLanguageUnitFromVerb(Verb verb, Pronoun pronoun, int time ){
        return verbService.getLanguageUnit(verb, pronoun, time);
    }

    public boolean isUnitExist (Verb verb, Pronoun pronoun, int time){
        return verbService.isUnitExist(verb, pronoun, time);
    }

}
