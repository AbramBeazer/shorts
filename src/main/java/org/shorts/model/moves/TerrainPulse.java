package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class TerrainPulse extends Move implements PulseMove {

    public TerrainPulse() {
        super("Terrain Pulse", 50, 100, Type.NORMAL, Category.SPECIAL, Range.NORMAL, 16, false, 0);
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        if (battle.getTerrain() != Terrain.NONE && user.isGrounded()) {
            return 2;
        } else {
            return 1;
        }
    }

    //TODO: Implement changing type based on terrain.
}
