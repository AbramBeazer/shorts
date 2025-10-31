package org.shorts.model.moves.hex;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class Hex extends Move {

    static final int MULTIPLIER = 2;

    public Hex() {
        super("Hex", 65, 100, Type.GHOST, Category.SPECIAL, Range.NORMAL, 16, false, 0);
    }

    @Override
    public double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        final double base = super.getPowerMultipliers(user, target, battle);
        return (target.getStatus() != null && target.getStatus().getType() != StatusType.NONE)
            ? base * MULTIPLIER
            : base;
    }
}
