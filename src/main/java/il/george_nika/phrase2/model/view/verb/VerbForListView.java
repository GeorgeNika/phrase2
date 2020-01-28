package il.george_nika.phrase2.model.view.verb;

import il.george_nika.phrase2.model.data.verb.Verb;
import il.george_nika.phrase2.model.view.AbstractEntityForListView;
import lombok.Data;

@Data
public class VerbForListView extends AbstractEntityForListView {

    private boolean actionVerb;

    public VerbForListView(Verb verb) {
        super(verb.getId(), verb.getInfinitive(), verb.getVerbDataCollection().size());
        this.actionVerb = false;
    }
}
