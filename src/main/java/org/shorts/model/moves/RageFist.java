package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class RageFist extends Move implements PunchingMove{
    public RageFist() {
        super("Rage Fist", 50, 100, Type.GHOST, Category.PHYSICAL, Range.NORMAL, 16, true, 100);
    }

    //TODO: Implement Rage-building mechanic
}
