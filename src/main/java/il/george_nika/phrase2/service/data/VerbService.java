package il.george_nika.phrase2.service.data;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.dao.ActionVerbRepository;
import il.george_nika.phrase2.model.dao.LanguageUnitRepository;
import il.george_nika.phrase2.model.dao.VerbDataRepository;
import il.george_nika.phrase2.model.dao.VerbRepository;
import il.george_nika.phrase2.model.pronoun.Pronoun;
import il.george_nika.phrase2.model.verb.ActionVerb;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.verb.VerbData;
import il.george_nika.phrase2.model.view.verb.VerbForDetailView;
import il.george_nika.phrase2.service.RandomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VerbService {

    private final VerbRepository verbRepository;
    private final VerbDataRepository verbDataRepository;
    private final LanguageUnitRepository languageUnitRepository;
    private final ActionVerbRepository actionVerbRepository;

    public VerbService (VerbRepository verbRepository,
                        VerbDataRepository verbDataRepository,
                        LanguageUnitRepository languageUnitRepository,
                        ActionVerbRepository actionVerbRepository){
        this.verbRepository = verbRepository;
        this.verbDataRepository = verbDataRepository;
        this.languageUnitRepository = languageUnitRepository;
        this.actionVerbRepository = actionVerbRepository;
    }

    public Verb getVerbById(Integer id){
        return verbRepository.getVerbById(id);
    }

    public List<Integer> getAllActionVerbIds(){
        return actionVerbRepository.getAllActionVerbIds();
    }

    public boolean isTimeExist(Verb verb, Integer time){

        return verb.getVerbDataCollection().stream().anyMatch(verbData -> verbData.getTime() == time);
    }

    public boolean isUnitExist(Verb verb, int gender, int quantity, int person, int time){

        return verb.getVerbDataCollection().stream()
                .anyMatch(verbData -> verbData.getTime() == time
                        && verbData.getGender() == gender
                        && verbData.getQuantity() == quantity
                        && verbData.getPerson() == person);
    }

    public boolean isUnitExist(Verb verb, Pronoun pronoun, int time){
        return isUnitExist(verb, pronoun.getGender(), pronoun.getQuantity(), pronoun.getPerson(), time);
    }

    public Page<Verb> getVerbsPage(int page, int itemsOnPage, String filter){
        Pageable pageable = new PageRequest(page, itemsOnPage, new Sort(Sort.Direction.ASC, "id"));
        if (filter.isEmpty()){
            Page<Verb> tempPage = verbRepository.getVerbsWithoutFilter(pageable);
            if ((tempPage.getContent().size()==0) && (page != 0)){
                pageable = new PageRequest(0, itemsOnPage, new Sort(Sort.Direction.ASC, "id"));
                tempPage = verbRepository.getVerbsWithoutFilter(pageable);
            }
            return tempPage;
        } else {
            Page<Verb> tempPage = verbRepository.getVerbsWithFilter(pageable, "%"+filter+"%");
            if ((tempPage.getContent().size()==0) && (page != 0)){
                pageable = new PageRequest(0, itemsOnPage, new Sort(Sort.Direction.ASC, "id"));
                tempPage = verbRepository.getVerbsWithFilter(pageable, "%"+filter+"%");
            }
            return tempPage;
        }
    }

    public Verb getRandomVerb() {
        return verbRepository.getRandomVerb();
    }

    public Verb getRandomActionVerb() {
        return actionVerbRepository.getRandomActionVerb().getVerb();
    }

    public LanguageUnit getLanguageUnitByPronounByTime(Verb verb, Pronoun pronoun, int time){

        Optional<VerbData> result = verb.getVerbDataCollection().stream()
                .filter(verbData -> verbData.getTime() == time
                        && verbData.getGender() == pronoun.getGender()
                        && verbData.getQuantity() == pronoun.getQuantity()
                        && verbData.getPerson() == pronoun.getPerson())
                .findFirst();

        if (result.isPresent()){
            return result.get().getLanguageUnit();
        }

        throw new RuntimeException("don't find " + pronoun + " for " + time + " verb N-"+verb.getId());
    }

    public Integer saveVerbByVerbForDetailView(VerbForDetailView verbForDetailView){
        Verb verb ;
        if (verbForDetailView.getId()==null || verbForDetailView.getId()==0) {
            verb = new Verb();
            verb.setInfinitive(new LanguageUnit());
            verb.setVerbDataCollection(new ArrayList<>());
        } else {
            verb = getVerbById(verbForDetailView.getId());
        }

        updateInfinitive(verb, verbForDetailView);
        updateVerbDataCollection(verb, verbForDetailView);

        verbRepository.save(verb);

        return verb.getId();
    }

    private void updateInfinitive(Verb verb, VerbForDetailView verbForDetailView){
        verb.getInfinitive().updateLanguageUnit(verbForDetailView.getInfinitive());
    }

    private void updateVerbDataCollection(Verb verb, VerbForDetailView verbForDetailView){
        Map<Integer, VerbData> mapForUpdate = new HashMap<>();
        for (VerbData loopVerbData : verb.getVerbDataCollection()){
            mapForUpdate.put(loopVerbData.getId(), loopVerbData);
        }
        for (VerbData loopVerbData : verbForDetailView.getVerbDataCollection()){
            VerbData tempVerbDate;
            if (loopVerbData.getId()== null || loopVerbData.getId() == 0){
                tempVerbDate = new VerbData();
                tempVerbDate.setLanguageUnit(new LanguageUnit());
                tempVerbDate.setVerb(verb);
                verb.getVerbDataCollection().add(tempVerbDate);
            } else {
                tempVerbDate = mapForUpdate.get(loopVerbData.getId());
                mapForUpdate.remove(loopVerbData.getId());
            }
            tempVerbDate.updateVerbDate(loopVerbData);
        }
        for (VerbData loopVerbData: mapForUpdate.values()){
            deleteVerbData(loopVerbData);
        }
    }

    private void deleteVerb(Verb verb){
        for (VerbData loopVerbData : verb.getVerbDataCollection()){
            deleteVerbData(loopVerbData);
        }
        languageUnitRepository.delete(verb.getInfinitive().getId());
        verbRepository.delete(verb.getId());
    }

    private void deleteVerbData(VerbData verbData){
        languageUnitRepository.delete(verbData.getLanguageUnit().getId());
        verbDataRepository.delete(verbData.getId());
    }

    public boolean changeActionVerb(Integer id){
        Verb verb = getVerbById(id);
        ActionVerb actionVerb = actionVerbRepository.getByVerb(verb);
        if (actionVerb == null){
            actionVerb = new ActionVerb();
            actionVerb.setVerb(verb);
            actionVerbRepository.save(actionVerb);
            return true;
        } else {
            actionVerbRepository.delete(actionVerb);
            return false;
        }
    }
}
