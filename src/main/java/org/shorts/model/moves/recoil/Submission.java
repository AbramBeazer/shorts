package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Submission extends RecoilAttack {

    public Submission() {
        super("Submission", 80, 80, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 32, true, 0, 0.25);
    }
}
