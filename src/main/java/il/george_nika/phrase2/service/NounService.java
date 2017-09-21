package il.george_nika.phrase2.service;

import il.george_nika.phrase2.model.dao.NounRepository;
import il.george_nika.phrase2.model.noun.Noun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NounService {

    private List<Noun> allNouns;

    private NounRepository nounRepository;
    private RandomService randomService;

    @Autowired
    public NounService (NounRepository nounRepository, RandomService randomService) {
        this.nounRepository = nounRepository;
        this.randomService = randomService;
    }

    private List<Noun> getAllNouns(){
        if (allNouns == null){
            allNouns = nounRepository.findAll();
        }
        return allNouns;
    }

    public void saveNoun(Noun noun){
        nounRepository.save(noun);
    }

    public Noun getRandomNoun(){

        return getAllNouns().get(randomService.getRandom(getAllNouns().size()));
    }

    public Integer getNounsQuantity(){
        return getAllNouns().size();
    }

}
