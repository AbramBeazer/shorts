package org.shorts.model.moves.screenremoving;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class RagingBull extends ScreenRemovingMove {

    public RagingBull() {
        super("Raging Bull", 90, 100, Type.NORMAL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 0);
    }

    //TODO: Make sure this changes type if the user is Paldean Tauros.
}
