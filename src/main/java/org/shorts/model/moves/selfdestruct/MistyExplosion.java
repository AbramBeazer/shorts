package org.shorts.model.moves.selfdestruct;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

//TODO: Does this move fail under the same conditions as Self-Destruct?
// Bulbapedia doesn't specify...
public class MistyExplosion extends SelfDestructingMove {

    public MistyExplosion() {
        super("Misty Explosion", 100, Type.FAIRY, Category.SPECIAL);
    }

    static final double MULTIPLIER = 1.5;

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        double multiplier = 1;
        if (user.isGrounded() && Terrain.MISTY.equals(battle.getTerrain())) {
            multiplier = 1.5;
        }
        return multiplier * super.getPowerMultipliers(user, target, battle);
    }
}
