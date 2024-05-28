package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;

public class Magnitude extends Move {

    public Magnitude() {
        super("Magnitude", 0, 100, Type.GROUND, Category.PHYSICAL, Range.ALL_ADJACENT, 48, false, 0);
    }

    @Override
    public double getPower() {

        //TODO: Implement
        return 0;
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        final double multiplier =
            battle.getTerrain() == Terrain.GRASSY && target.isGrounded() && !target.hasVolatileStatus(SEMI_INVULNERABLE)
                ? 2
                : 1;
        //TODO: Does terrain affect a mon with an Iron Ball or Thousand Arrows?
        return multiplier * super.getPowerMultipliers(user, target, battle);
    }
}
