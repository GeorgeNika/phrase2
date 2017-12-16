package il.george_nika.phrase2.model.view.adverb;

import il.george_nika.phrase2.model.LanguageUnit;
import il.george_nika.phrase2.model.adverb.Adverb;
import il.george_nika.phrase2.model.noun.Noun;
import il.george_nika.phrase2.model.noun.NounData;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AdverbForDetailView {

    private Integer id;
    private LanguageUnit mainForm;


    public AdverbForDetailView(){}

    public AdverbForDetailView(Adverb adverb){
        this.id = adverb.getId();
        this.mainForm = adverb.getMainForm();
    }
}
