package il.george_nika.phrase2.model.view.verb;

import il.george_nika.phrase2.model.data.LanguageUnit;
import il.george_nika.phrase2.model.data.verb.Verb;
import il.george_nika.phrase2.model.data.verb.VerbData;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class VerbForDetailView {

    private Integer id;
    private LanguageUnit infinitive;
    private LanguageUnit preposition;
    private List<VerbData> verbDataCollection;


    public VerbForDetailView(){}

    public VerbForDetailView(Verb verb){
        this.id = verb.getId();
        this.infinitive = verb.getInfinitive();

        this.preposition = verb.getPreposition();

        this.verbDataCollection = verb.getVerbDataCollection().stream()
                .map(verbData -> new VerbData(verbData, false))
                .sorted(Comparator.comparingInt(verbData -> verbData.getTime()*1000 + verbData.getPerson()*100
                    + verbData.getQuantity()*10 + verbData.getGender()))
                .collect(Collectors.toList());

    }
}
