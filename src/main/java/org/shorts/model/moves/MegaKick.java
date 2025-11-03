package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class MegaKick extends Move implements KickingMove {
    public MegaKick() {
        super("Mega Kick", 120, 75, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 8, true, 0);
    }
}
