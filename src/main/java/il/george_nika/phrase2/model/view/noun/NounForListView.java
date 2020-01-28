package il.george_nika.phrase2.model.view.noun;

import il.george_nika.phrase2.model.data.noun.Noun;
import il.george_nika.phrase2.model.view.AbstractEntityForListView;

public class NounForListView extends AbstractEntityForListView {

    public NounForListView(Noun noun) {
        super(noun.getId(), noun.getMainForm(), noun.getNounDataCollection().size());
    }
}
