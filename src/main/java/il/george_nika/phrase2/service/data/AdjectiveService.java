package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.adjective.Adjective;
import il.george_nika.phrase2.model.adjective.AdjectiveData;
import il.george_nika.phrase2.model.dao.*;
import il.george_nika.phrase2.model.view.adjective.AdjectiveForDetailView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdjectiveService {

    private final AdjectiveRepository adjectiveRepository;
    private final AdjectiveDataRepository adjectiveDataRepository;
    private final LanguageUnitRepository languageUnitRepository;

    @Autowired
    public AdjectiveService(AdjectiveRepository adjectiveRepository,
                            AdjectiveDataRepository adjectiveDataRepository,
                            LanguageUnitRepository languageUnitRepository) {
        this.adjectiveRepository = adjectiveRepository;
        this.adjectiveDataRepository = adjectiveDataRepository;
        this.languageUnitRepository = languageUnitRepository;
    }

    private List<Adjective> getAllAdjectives(){
        return adjectiveRepository.findAll();
    }

    public Adjective getAdjectiveById(Integer id){
        return adjectiveRepository.getAdjectiveById(id);
    }

    public Page<Adjective> getAdjectivesPage(int page, int itemsOnPage, String filter){
        Pageable pageable = new PageRequest(page, itemsOnPage, new Sort(Sort.Direction.ASC, "id"));
        if (filter.isEmpty()){
            Page<Adjective> tempPage = adjectiveRepository.getAdjectivesWithoutFilter(pageable);
            if ((tempPage.getContent().size()==0) && (page != 0)){
                pageable = new PageRequest(0, itemsOnPage, new Sort(Sort.Direction.ASC, "id"));
                tempPage = adjectiveRepository.getAdjectivesWithoutFilter(pageable);
            }
            return tempPage;
        } else {
            Page<Adjective> tempPage = adjectiveRepository.getAdjectivesWithFilter(pageable, "%"+filter+"%");
            if ((tempPage.getContent().size()==0) && (page != 0)){
                pageable = new PageRequest(0, itemsOnPage, new Sort(Sort.Direction.ASC, "id"));
                tempPage = adjectiveRepository.getAdjectivesWithFilter(pageable, "%"+filter+"%");
            }
            return tempPage;
        }
    }

    public Integer saveAdjectiveByAdjectiveForDetailView(AdjectiveForDetailView adjectiveForDetailView){
        Adjective adjective ;
        if (adjectiveForDetailView.getId()==null || adjectiveForDetailView.getId()==0) {
            adjective = new Adjective();
            adjective.setAdjectiveDataCollection(new ArrayList<>());
        } else {
            adjective = getAdjectiveById(adjectiveForDetailView.getId());
        }

        updateAdjectiveDataCollection(adjective, adjectiveForDetailView);
        saveAdjective(adjective);
        return adjective.getId();
    }

    private void saveAdjective(Adjective adjective){
        if (adjective.getAdjectiveDataCollection().size() > 0){
            adjective.setMainForm(getMainForm(adjective));
        }
        adjectiveRepository.save(adjective);
    }

    private LanguageUnit getMainForm(Adjective adjective){
        return adjective.getAdjectiveDataCollection().stream()
                .sorted(Comparator.comparingInt(adjectiveData -> adjectiveData.getGender() * 10 + adjectiveData.getQuantity()))
                .findFirst().get().getLanguageUnit();
    }

    private void updateAdjectiveDataCollection(Adjective adjective, AdjectiveForDetailView adjectiveForDetailView){
        Map<Integer, AdjectiveData> mapForUpdate = new HashMap<>();
        for (AdjectiveData loopAdjectiveData : adjective.getAdjectiveDataCollection()){
            mapForUpdate.put(loopAdjectiveData.getId(), loopAdjectiveData);
        }
        for (AdjectiveData loopAdjectiveData : adjectiveForDetailView.getAdjectiveDataCollection()){
            AdjectiveData tempAdjectiveDate;
            if (loopAdjectiveData.getId()== null || loopAdjectiveData.getId() == 0){
                tempAdjectiveDate = new AdjectiveData();
                tempAdjectiveDate.setLanguageUnit(new LanguageUnit());
                tempAdjectiveDate.setAdjective(adjective);
                adjective.getAdjectiveDataCollection().add(tempAdjectiveDate);
            } else {
                tempAdjectiveDate = mapForUpdate.get(loopAdjectiveData.getId());
                mapForUpdate.remove(loopAdjectiveData.getId());
            }
            tempAdjectiveDate.updateAdjectiveDate(loopAdjectiveData);
        }
        for (AdjectiveData loopAdjectiveData: mapForUpdate.values()){
            deleteAdjectiveData(loopAdjectiveData);
        }
    }

    private void deleteAdjectiveData(AdjectiveData adjectiveData){
        languageUnitRepository.delete(adjectiveData.getLanguageUnit().getId());
        adjectiveDataRepository.delete(adjectiveData.getId());
    }

    public Adjective getRandomAdjective(){
        return adjectiveRepository.getRandomAdjective();
    }

    public LanguageUnit getLanguageUnitByGenderByQuantity(Adjective adjective, int gender, int quantity){
        Optional<AdjectiveData> tempAdjectiveData =  adjective.getAdjectiveDataCollection().stream()
                .filter(adjectiveData -> adjectiveData.getGender() == gender
                        && adjectiveData.getQuantity() == quantity)
                .findFirst();
        if (tempAdjectiveData.isPresent()){
            return tempAdjectiveData.get().getLanguageUnit();
        }
        throw new RuntimeException("don't find language unit for gender:" + gender
                + " and quantity:" + quantity + " in adjective N-"+adjective.getId());
    }

}
