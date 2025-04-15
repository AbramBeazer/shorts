package org.shorts.model.moves.screenremoving;

import org.shorts.model.moves.BitingMove;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class PsychicFangs extends ScreenRemovingMove implements BitingMove {

    public PsychicFangs() {
        super("Psychic Fangs", 85, 100, Type.PSYCHIC, Category.PHYSICAL, Range.NORMAL, 16, true, 0);
    }
}
