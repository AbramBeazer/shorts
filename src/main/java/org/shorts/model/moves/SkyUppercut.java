package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class SkyUppercut extends Move implements PunchingMove {

    public SkyUppercut() {
        super("Sky Uppercut", 85, 90, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL, 24, true, 0);
    }

    //TODO: Implement hitting semi-invulnerable targets
}
