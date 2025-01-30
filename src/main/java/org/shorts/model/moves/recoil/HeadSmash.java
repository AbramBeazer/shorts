package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class HeadSmash extends RecoilAttack {

    public HeadSmash() {
        super("Head Smash", 150, 80, Type.ROCK, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 8, true, 0, 0.5);
    }
}
