package il.george_nika.phrase2.model.view.adverb;

import il.george_nika.phrase2.model.data.LanguageUnit;
import il.george_nika.phrase2.model.data.adverb.Adverb;
import lombok.Data;

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
