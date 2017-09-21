package il.george_nika.phrase2.model.view;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.verb.Verb;
import il.george_nika.phrase2.model.verb.VerbData;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
public class VerbForView {

    private Integer id;
    private LanguageUnit infinitive;
    private List<VerbData> verbDataCollection;


    public VerbForView(){}

    public VerbForView(Verb verb){
        this.id = verb.getId();
        this.infinitive = verb.getInfinitive();
        this.verbDataCollection = new ArrayList<VerbData>();
        for (VerbData loopVerbData: verb.getVerbDataCollection()){
            VerbData tempVerbData = new VerbData();
            tempVerbData.setId(loopVerbData.getId());
            tempVerbData.setGender(loopVerbData.getGender());
            tempVerbData.setQuantity(loopVerbData.getQuantity());
            tempVerbData.setTime(loopVerbData.getTime());
            tempVerbData.setPerson(loopVerbData.getPerson());
            tempVerbData.setLanguageUnit(loopVerbData.getLanguageUnit());
            this.verbDataCollection.add(tempVerbData);
        }
        Collections.sort(verbDataCollection, new Comparator<VerbData>() {
            @Override
            public int compare(VerbData o1, VerbData o2) {
                if (!(isFullData(o1) && isFullData(o2))) {
                    return 0;
                }
                if (o1.getTime() == o2.getTime()){
                    if(o1.getPerson() == o2.getPerson()){
                        if (o1.getQuantity() == o2.getQuantity()){
                            return o1.getGender() - o2.getGender();
                        }
                        return o1.getQuantity()-o2.getQuantity();
                    }
                    return o1.getPerson() - o2.getPerson();
                }
                return o1.getTime()-o2.getTime();
            }

            private boolean isFullData(VerbData o){
                if ((o.getTime() == null)
                        || (o.getPerson() == null)
                        || (o.getQuantity() == null)
                        || (o.getGender() == null)){
                    return false;
                }
                return true;
            }
        });
    }
}
