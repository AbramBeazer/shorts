package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class WaveCrash extends RecoilAttack {

    public WaveCrash() {
        super("Wave Crash", 120, 100, Type.WATER, Category.PHYSICAL, Range.NORMAL, 16, true, 0, 1 / 3d);
    }
}

