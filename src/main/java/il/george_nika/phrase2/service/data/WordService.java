package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.view.adjective.AdjectiveForDetailView;
import il.george_nika.phrase2.model.view.adverb.AdverbForDetailView;
import il.george_nika.phrase2.model.view.noun.NounForDetailView;
import il.george_nika.phrase2.model.view.noun.NounForListView;
import il.george_nika.phrase2.model.view.verb.VerbForDetailView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static il.george_nika.phrase2.model.ModelConstants.*;

@Service
public class WordService {

    private final VerbService verbService;
    private final NounService nounService;
    private final AdjectiveService adjectiveService;
    private final AdverbService adverbService;

    @Autowired
    public WordService(VerbService verbService, NounService nounService,
                       AdjectiveService adjectiveService, AdverbService adverbService) {
        this.verbService = verbService;
        this.nounService = nounService;
        this.adjectiveService = adjectiveService;
        this.adverbService = adverbService;
    }

    public List<LanguageUnit> getWordInfo(String type, int wordId){
        if (type.equals(VERB_TYPE)){
            ArrayList<LanguageUnit> result = new ArrayList<>();
            VerbForDetailView verbInfo = new VerbForDetailView(verbService.getVerbById(wordId));
            result.add(verbInfo.getInfinitive());
            result.addAll(verbInfo.getVerbDataCollection().stream()
                    .map(verbData -> verbData.getLanguageUnit()).collect(Collectors.toList()));
            return result;
        }
        if (type.equals(NOUN_TYPE)){
            NounForDetailView nounInfo = new NounForDetailView(nounService.getNounById(wordId));
            return nounInfo.getNounDataCollection().stream()
                    .map(nounData -> nounData.getLanguageUnit()).collect(Collectors.toList());
        }
        if (type.equals(ADJECTIVE_TYPE)){
            AdjectiveForDetailView adjectiveInfo = new AdjectiveForDetailView(adjectiveService.getAdjectiveById(wordId));
            return adjectiveInfo.getAdjectiveDataCollection().stream()
                    .map(adjectiveData -> adjectiveData.getLanguageUnit()).collect(Collectors.toList());
        }
        if (type.equals(ADVERB_TYPE)){
            AdverbForDetailView adverbInfo = new AdverbForDetailView(adverbService.getAdverbById(wordId));
            ArrayList<LanguageUnit> result = new ArrayList<>();
            result.add(adverbInfo.getMainForm());
            return result;
        }

        throw new RuntimeException("unknown type of word "+type);
    }
}
