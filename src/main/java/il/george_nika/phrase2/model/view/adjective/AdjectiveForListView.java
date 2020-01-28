package il.george_nika.phrase2.model.view.adjective;

import il.george_nika.phrase2.model.data.adjective.Adjective;
import il.george_nika.phrase2.model.view.AbstractEntityForListView;

public class AdjectiveForListView extends AbstractEntityForListView {

    public AdjectiveForListView(Adjective adjective) {
        super(adjective.getId(), adjective.getMainForm(), adjective.getAdjectiveDataCollection().size());
    }
}
