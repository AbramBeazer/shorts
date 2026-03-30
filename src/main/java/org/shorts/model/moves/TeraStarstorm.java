package org.shorts.model.moves;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class TeraStarstorm extends Move {

    public TeraStarstorm() {
        super("Tera Starstorm", 120, 100, Type.NORMAL, Category.SPECIAL, Range.NORMAL, 8, false, 100);
    }

    @Override
    public Range getRange(Pokemon user) {
        if (user.isTera() && user.getTeraType() instanceof Type.StellarType) {
            return Range.ALL_ADJACENT_OPPONENTS;
        } else {
            return Range.NORMAL;
        }
    }
}
