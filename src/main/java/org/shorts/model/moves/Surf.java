package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;

public class Surf extends SpecialMove {
    //TODO: Verify power, PP, etc.
    public Surf() {
        super("Surf", 95, 100, Type.WATER, 24, false, 0);
    }

    @Override
    protected double getOtherMultiplier(Pokemon user, Pokemon target, Battle battle) {
        final double multiplier = super.getOtherMultiplier(user, target, battle);
        if (target.hasVolatileStatus(SEMI_INVULNERABLE) && target.getVolatileStatus(SEMI_INVULNERABLE).getMove() instanceof Dive) {
            return 2 * multiplier;
        } else return multiplier;
    }
}
