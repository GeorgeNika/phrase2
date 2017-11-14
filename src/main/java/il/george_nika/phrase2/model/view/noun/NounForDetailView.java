package il.george_nika.phrase2.model.view.noun;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.noun.Noun;
import il.george_nika.phrase2.model.noun.NounData;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class NounForDetailView {

    private Integer id;
    private LanguageUnit mainForm;
    private List<NounData> nounDataCollection;


    public NounForDetailView(){}

    public NounForDetailView(Noun noun){
        this.id = noun.getId();
        this.nounDataCollection = noun.getNounDataCollection().stream()
                .map(nounData -> new NounData(nounData, false))
                .sorted(Comparator.comparingInt(nounData -> nounData.getGenderRU()*10 + nounData.getQuantityRU()))
                .collect(Collectors.toList());
    }
}
