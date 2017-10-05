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
import il.george_nika.phrase2.model.view.VerbForView;
import il.george_nika.phrase2.model.view.VerbInfo;
import il.george_nika.phrase2.service.RandomService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VerbService {

    private List<Integer> allVerbsIds;
    private List<ActionVerb> allActionVerbs;

    private static int LAST_VERB_ID_PERCENT_PRIORITY = 20;
    private static int VERBS_REFRESH_LIMIT = 50;
    private static int ACTION_VERBS_REFRESH_LIMIT = 20;

    private int verbsRefreshTrigger;
    private int actionVerbsRefreshTrigger;

    private VerbRepository verbRepository;
    private VerbDataRepository verbDataRepository;
    private LanguageUnitRepository languageUnitRepository;
    private ActionVerbRepository actionVerbRepository;
    private RandomService randomService;

    public VerbService (VerbRepository verbRepository,
                        VerbDataRepository verbDataRepository,
                        LanguageUnitRepository languageUnitRepository,
                        ActionVerbRepository actionVerbRepository,
                        RandomService randomService){
        this.verbRepository = verbRepository;
        this.verbDataRepository = verbDataRepository;
        this.languageUnitRepository = languageUnitRepository;
        this.actionVerbRepository = actionVerbRepository;
        this.randomService = randomService;
    }

    private List<Integer> getAllVerbsIds(){
        verbsRefreshTrigger ++;
        if ((allVerbsIds == null) || (verbsRefreshTrigger >= VERBS_REFRESH_LIMIT)){
            allVerbsIds = verbRepository.getAllIds();
            Collections.sort(allVerbsIds);
            verbsRefreshTrigger = 0;
        }
        return allVerbsIds;
    }

    public Verb getVerbById(Integer id){
        return verbRepository.getVerbById(id);
    }

    private List<ActionVerb> getAllActionVerbs(){
        actionVerbsRefreshTrigger ++;
        if ((allActionVerbs == null) || (actionVerbsRefreshTrigger >= ACTION_VERBS_REFRESH_LIMIT)){
            allActionVerbs = actionVerbRepository.findAll();
            actionVerbsRefreshTrigger = 0;
        }
        return allActionVerbs;
    }

    public boolean isTimeExist(Verb verb, Integer time){
        for (VerbData loopVerbData : verb.getVerbDataCollection()){
            if (loopVerbData.getTime() == time) {
                return true;
            }
        }
        return false;
    }

    public boolean isUnitExist(Verb verb, int gender, int quantity, int person, int time){
        for (VerbData loopVerbData : verb.getVerbDataCollection()){
            if (loopVerbData.getTime() == time) {
                if ((loopVerbData.getGender() == gender)
                    && (loopVerbData.getQuantity() == quantity)
                    && (loopVerbData.getPerson() == person)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isUnitExist(Verb verb, Pronoun pronoun, int time){
        return isUnitExist(verb, pronoun.getGender(), pronoun.getQuantity(), pronoun.getPerson(), time);
    }

    public List<VerbInfo> getAllVerbInfo(){
        List<VerbInfo> result = new ArrayList<VerbInfo>();
        List<Integer> allActiveVerbsId = actionVerbRepository.getAllActionVerbIds();
        for (Verb loopVerb : verbRepository.findAll()){
           VerbInfo tempVerbInfo = new VerbInfo(loopVerb);
           if (allActiveVerbsId.contains(loopVerb.getId())) {
               tempVerbInfo.setActionVerb(true);
           }
           result.add(tempVerbInfo);
        }
        Collections.sort(result, new Comparator<VerbInfo>() {
            @Override
            public int compare(VerbInfo o1, VerbInfo o2) {
                return o1.getId() - o2.getId();
            }
        });
        return result;
    }

    public Verb getRandomVerb() {
        int id = randomService.getRandomWithPriorityOnLast(getAllVerbsIds().size(),LAST_VERB_ID_PERCENT_PRIORITY);
        return getVerbById(getAllVerbsIds().get(id));
    }

    public Verb getRandomActionVerb() {
        return getAllActionVerbs().get(randomService.getRandom((getAllActionVerbs().size()))).getVerb();
    }

    public LanguageUnit getLanguageUnit(Verb verb, Pronoun pronoun, int time){

        for (VerbData loopVerbData : verb.getVerbDataCollection()){
            if (loopVerbData.getTime() != time) continue;
            if (loopVerbData.getPerson() != pronoun.getPerson()) continue;
            if (loopVerbData.getGender() != pronoun.getGender()) continue;
            if (loopVerbData.getQuantity() != pronoun.getQuantity()) continue;
            return loopVerbData.getLanguageUnit();
        }

        throw new RuntimeException("don't find " + pronoun + " for " + time + " verb N-"+verb.getId());
    }

    public void saveVerbByVerbForView(VerbForView verbForView){
        Verb verb ;
        if (verbForView.getId()==null || verbForView.getId()==0) {
            verb = new Verb();
            verb.setInfinitive(new LanguageUnit());
            verb.setVerbDataCollection(new ArrayList<VerbData>());
        } else {
            verb = getVerbById(verbForView.getId());
        }

        updateInfinitive(verb, verbForView);
        updateVerbDataCollection(verb, verbForView);

        verbRepository.save(verb);
    }

    private void updateInfinitive(Verb verb, VerbForView verbForView){
        verb.getInfinitive().updateLanguageUnit(verbForView.getInfinitive());
    }

    private void updateVerbDataCollection(Verb verb, VerbForView verbForView){
        Map<Integer, VerbData> mapForUpdate = new HashMap<Integer, VerbData>();
        for (VerbData loopVerbData : verb.getVerbDataCollection()){
            mapForUpdate.put(loopVerbData.getId(), loopVerbData);
        }
        for (VerbData loopVerbData : verbForView.getVerbDataCollection()){
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
