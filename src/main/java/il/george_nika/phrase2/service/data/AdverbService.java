package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.adverb.Adverb;
import il.george_nika.phrase2.model.dao.AdverbRepository;
import il.george_nika.phrase2.model.dao.LanguageUnitRepository;
import il.george_nika.phrase2.model.view.adverb.AdverbForDetailView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdverbService {

    private final AdverbRepository adverbRepository;
    private final LanguageUnitRepository languageUnitRepository;

    @Autowired
    public AdverbService(AdverbRepository adverbRepository,
                         LanguageUnitRepository languageUnitRepository) {
        this.adverbRepository = adverbRepository;
        this.languageUnitRepository = languageUnitRepository;
    }

    private List<Adverb> getAllAdverbs(){
        return adverbRepository.findAll();
    }

    public Adverb getAdverbById(Integer id){
        return adverbRepository.getAdverbById(id);
    }

    public Page<Adverb> getAdverbsPage(int page, int itemsOnPage, String filter){
        Pageable pageable = new PageRequest(page, itemsOnPage, new Sort(Sort.Direction.ASC, "id"));
        if (filter.isEmpty()){
            Page<Adverb> tempPage = adverbRepository.getAdverbsWithoutFilter(pageable);
            if ((tempPage.getContent().size()==0) && (page != 0)){
                pageable = new PageRequest(0, itemsOnPage, new Sort(Sort.Direction.ASC, "id"));
                tempPage = adverbRepository.getAdverbsWithoutFilter(pageable);
            }
            return tempPage;
        } else {
            Page<Adverb> tempPage = adverbRepository.getAdverbsWithFilter(pageable, "%"+filter+"%");
            if ((tempPage.getContent().size()==0) && (page != 0)){
                pageable = new PageRequest(0, itemsOnPage, new Sort(Sort.Direction.ASC, "id"));
                tempPage = adverbRepository.getAdverbsWithFilter(pageable, "%"+filter+"%");
            }
            return tempPage;
        }
    }

    public Integer saveAdverbByAdverbForDetailView(AdverbForDetailView adverbForDetailView){
        Adverb adverb ;
        if (adverbForDetailView.getId()==null || adverbForDetailView.getId()==0) {
            adverb = new Adverb();
            adverb.setMainForm(new LanguageUnit());
        } else {
            adverb = getAdverbById(adverbForDetailView.getId());
        }

        updateLanguageUnit(adverb, adverbForDetailView);
        saveAdverb(adverb);
        return adverb.getId();
    }

    private void updateLanguageUnit(Adverb adverb, AdverbForDetailView adverbForDetailView){
        adverb.getMainForm().updateLanguageUnit(adverbForDetailView.getMainForm());
    }

    public void saveAdverb(Adverb adverb){
        adverbRepository.save(adverb);
    }


    public Adverb getRandomAdverb(){
        return adverbRepository.getRandomAdverb();
    }

}
