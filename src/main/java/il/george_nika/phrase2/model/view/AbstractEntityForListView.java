package il.george_nika.phrase2.model.view;

import il.george_nika.phrase2.model.LanguageUnit;
import lombok.Data;

@Data
abstract public class AbstractEntityForListView {

    private Integer id;
    private String hebrew = "";
    private String russian = "";
    private Integer childQuantity;

    public AbstractEntityForListView(Integer id, LanguageUnit languageUnit, Integer childQuantity) {
        this.id = id;
        if (languageUnit != null) {
            this.hebrew = languageUnit.getHebrew();
            this.russian = languageUnit.getRussian();
        }
        this.childQuantity = childQuantity;
    }
}
