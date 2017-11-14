package il.george_nika.phrase2.model.view;

import lombok.Data;

@Data
abstract public class AbstractEntityForListView {

    private Integer id;
    private String hebrew = "";
    private String russian = "";
    private Integer childQuantity;

    public AbstractEntityForListView(Integer id, String hebrew, String russian, Integer childQuantity) {
        this.id = id;
        this.hebrew = hebrew;
        this.russian = russian;
        this.childQuantity = childQuantity;
    }
}
