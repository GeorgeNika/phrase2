package il.george_nika.phrase2.model.view.noun;

import il.george_nika.phrase2.model.noun.Noun;
import il.george_nika.phrase2.model.view.AbstractEntityForListView;

public class NounForListView extends AbstractEntityForListView {

    public NounForListView(Noun noun) {
        super(noun.getId(), noun.getMainForm().getHebrew(),
                noun.getMainForm().getRussian(), noun.getNounDataCollection().size());
    }
}
