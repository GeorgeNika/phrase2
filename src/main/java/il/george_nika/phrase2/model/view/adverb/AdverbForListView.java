package il.george_nika.phrase2.model.view.adverb;

import il.george_nika.phrase2.model.data.adverb.Adverb;
import il.george_nika.phrase2.model.view.AbstractEntityForListView;

public class AdverbForListView extends AbstractEntityForListView {

    public AdverbForListView(Adverb adverb) {
        super(adverb.getId(), adverb.getMainForm(), 0);
    }
}
