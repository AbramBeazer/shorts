package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;

public class Bulldoze extends PhysicalMove {
    public Bulldoze(){
        super("Bulldoze", 60, 100, Type.GROUND, 32, false, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.changeSpeed(-1);
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        final double multiplier = battle.getTerrain() == Terrain.GRASSY && target.isGrounded() && !target.hasVolatileStatus(SEMI_INVULNERABLE) ? 2 : 1;
        //TODO: Does terrain affect a mon with an Iron Ball or Thousand Arrows?
        return multiplier * super.getPowerMultipliers(user, target, battle);
    }


}
