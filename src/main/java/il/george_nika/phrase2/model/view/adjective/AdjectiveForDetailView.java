package il.george_nika.phrase2.model.view.adjective;

import il.george_nika.phrase2.model.data.LanguageUnit;
import il.george_nika.phrase2.model.data.adjective.Adjective;
import il.george_nika.phrase2.model.data.adjective.AdjectiveData;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AdjectiveForDetailView {

    private Integer id;
    private LanguageUnit mainForm;
    private List<AdjectiveData> adjectiveDataCollection;


    public AdjectiveForDetailView(){}

    public AdjectiveForDetailView(Adjective adjective){
        this.id = adjective.getId();
        this.adjectiveDataCollection = adjective.getAdjectiveDataCollection().stream()
                .map(adjectiveData -> new AdjectiveData(adjectiveData, false))
                .sorted(Comparator.comparingInt(adjectiveData -> adjectiveData.getGender()*10 + adjectiveData.getQuantity()))
                .collect(Collectors.toList());
    }
}
