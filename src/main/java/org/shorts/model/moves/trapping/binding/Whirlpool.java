package org.shorts.model.moves.trapping.binding;

import org.shorts.model.moves.CanHitDive;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Whirlpool extends BindingMove implements CanHitDive {

    public Whirlpool() {
        super("Whirlpool", 35, 85, Type.WATER, Category.SPECIAL, Range.NORMAL, 24, false, 100);
    }
}
