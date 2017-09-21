package il.george_nika.phrase2.model.view;

import il.george_nika.phrase2.model.verb.Verb;
import lombok.Data;

@Data
public class VerbInfo {

    private Integer id;
    private String hebrew = "";
    private String russian = "";
    private boolean actionVerb;
    private Integer childQuantity;

    public VerbInfo(Verb verb) {
        this.id = verb.getId();
        this.hebrew = verb.getInfinitive().getHebrew();
        this.russian = verb.getInfinitive().getRussian();
        this.childQuantity = verb.getVerbDataCollection().size();
        this.actionVerb = false;
    }
}
