package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;

public class Earthquake extends PhysicalMove {

    public Earthquake() {
        super("Earthquake", 100, 100, Type.GROUND, 16, false, 0);
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        final double multiplier = battle.getTerrain() == Terrain.GRASSY && target.isGrounded() && !target.hasVolatileStatus(SEMI_INVULNERABLE) ? 2 : 1;
        //TODO: Does terrain affect a mon with an Iron Ball or Thousand Arrows?
        return multiplier * super.getPowerMultipliers(user, target, battle);
    }

}
