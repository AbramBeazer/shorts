package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class WoodHammer extends RecoilAttack {

    public WoodHammer() {
        super("Wood Hammer", 120, 100, Type.GRASS, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 24, true, 0, 1 / 3d);
    }
}
