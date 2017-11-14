package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.dao.LanguageUnitRepository;
import il.george_nika.phrase2.model.dao.NounDataRepository;
import il.george_nika.phrase2.model.dao.NounRepository;
import il.george_nika.phrase2.model.noun.Noun;
import il.george_nika.phrase2.model.noun.NounData;
import il.george_nika.phrase2.model.view.noun.NounForDetailView;
import il.george_nika.phrase2.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NounService {

    private NounRepository nounRepository;
    private NounDataRepository nounDataRepository;
    private LanguageUnitRepository languageUnitRepository;

    @Autowired
    public NounService (NounRepository nounRepository,
                        NounDataRepository nounDataRepository,
                        LanguageUnitRepository languageUnitRepository) {
        this.nounRepository = nounRepository;
        this.nounDataRepository = nounDataRepository;
        this.languageUnitRepository = languageUnitRepository;
    }

    private List<Noun> getAllNouns(){
        return nounRepository.findAll();
    }

    public Noun getNounById(Integer id){
        return nounRepository.getNounById(id);
    }

    public Page<Noun> getNounsPage(int page, int itemsOnPage, String filter){
        Pageable pageable = new PageRequest(page, itemsOnPage, new Sort(Sort.Direction.ASC, "id"));
        if (filter.isEmpty()){
            Page<Noun> tempPage = nounRepository.getNounsWithoutFilter(pageable);
            if ((tempPage.getContent().size()==0) && (page != 0)){
                pageable = new PageRequest(0, itemsOnPage, new Sort(Sort.Direction.ASC, "id"));
                tempPage = nounRepository.getNounsWithoutFilter(pageable);
            }
            return tempPage;
        } else {
            Page<Noun> tempPage = nounRepository.getNounsWithFilter(pageable, "%"+filter+"%");
            if ((tempPage.getContent().size()==0) && (page != 0)){
                pageable = new PageRequest(0, itemsOnPage, new Sort(Sort.Direction.ASC, "id"));
                tempPage = nounRepository.getNounsWithFilter(pageable, "%"+filter+"%");
            }
            return tempPage;
        }
    }

    public Integer saveNounByNounForDetailView(NounForDetailView nounForDetailView){
        Noun noun ;
        if (nounForDetailView.getId()==null || nounForDetailView.getId()==0) {
            noun = new Noun();
            noun.setNounDataCollection(new ArrayList<>());
        } else {
            noun = getNounById(nounForDetailView.getId());
        }

        updateNounDataCollection(noun, nounForDetailView);
        saveNoun(noun);
        return noun.getId();
    }

    public void saveNoun(Noun noun){
        if (noun.getNounDataCollection().size() > 0){
            noun.setMainForm(getMainForm(noun));
        }
        nounRepository.save(noun);
    }

    private LanguageUnit getMainForm(Noun noun){
        return noun.getNounDataCollection().stream()
                .sorted(Comparator.comparingInt(nounData -> nounData.getGenderRU() * 10 + nounData.getQuantityRU()))
                .findFirst().get().getLanguageUnit();
    }

    private void updateNounDataCollection(Noun noun, NounForDetailView nounForDetailView){
        Map<Integer, NounData> mapForUpdate = new HashMap<>();
        for (NounData loopNounData : noun.getNounDataCollection()){
            mapForUpdate.put(loopNounData.getId(), loopNounData);
        }
        for (NounData loopNounData : nounForDetailView.getNounDataCollection()){
            NounData tempNounDate;
            if (loopNounData.getId()== null || loopNounData.getId() == 0){
                tempNounDate = new NounData();
                tempNounDate.setLanguageUnit(new LanguageUnit());
                tempNounDate.setNoun(noun);
                noun.getNounDataCollection().add(tempNounDate);
            } else {
                tempNounDate = mapForUpdate.get(loopNounData.getId());
                mapForUpdate.remove(loopNounData.getId());
            }
            tempNounDate.updateNounDate(loopNounData);
        }
        for (NounData loopNounData: mapForUpdate.values()){
            deleteNounData(loopNounData);
        }
    }

    private void deleteNounData(NounData nounData){
        languageUnitRepository.delete(nounData.getLanguageUnit().getId());
        nounDataRepository.delete(nounData.getId());
    }

    public Noun getRandomNoun(){
        return nounRepository.getRandomNoun();
    }

}
