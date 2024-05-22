package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;

public class Magnitude extends PhysicalMove {

    //TODO: Verify contact, PP, etc.
    public Magnitude(){
        super("Magnitude", 0, 100, Type.GROUND, 24, false,0);
    }

    @Override
    public double getPower() {

        //TODO: Implement
        return 0;
    }

    @Override
    protected double getOtherMultiplier(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Verify that this works the same way as Earthquake.
        double multiplier = super.getOtherMultiplier(user, target, battle);
        if (user.hasVolatileStatus(SEMI_INVULNERABLE) && target.getVolatileStatus(SEMI_INVULNERABLE)
                .getMove() instanceof Dig) {
            multiplier *= 2;
        }
        if (battle.getTerrain().equals(Terrain.GRASSY)) {
            multiplier *= 0.5;
        }
        return multiplier;
    }
}
