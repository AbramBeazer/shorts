package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;

public class Bulldoze extends Move {

    public Bulldoze() {
        super("Bulldoze", 60, 100, Type.GROUND, Category.PHYSICAL, Range.ALL_ADJACENT, 32, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.SPEED)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeSpeed(-1);
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
