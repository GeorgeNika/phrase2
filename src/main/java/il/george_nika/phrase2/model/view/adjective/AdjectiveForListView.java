package il.george_nika.phrase2.model.view.adjective;

import il.george_nika.phrase2.model.adjective.Adjective;
import il.george_nika.phrase2.model.view.AbstractEntityForListView;

public class AdjectiveForListView extends AbstractEntityForListView {

    public AdjectiveForListView(Adjective adjective) {
        super(adjective.getId(), adjective.getMainForm().getHebrew(),
                adjective.getMainForm().getRussian(), adjective.getAdjectiveDataCollection().size());
    }
}
