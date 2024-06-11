package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Psyblade extends Move implements SlicingMove {

    protected static final double PSYBLADE_MULTIPLIER = 1.5;

    public Psyblade() {
        super("Psyblade", 80, 100, Type.PSYCHIC, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, true, 0);
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        if (battle.getTerrain() == Terrain.ELECTRIC && user.isGrounded()) {
            return super.getPowerMultipliers(user, target, battle) * PSYBLADE_MULTIPLIER;
        } else {
            return super.getPowerMultipliers(user, target, battle);
        }
    }
}
