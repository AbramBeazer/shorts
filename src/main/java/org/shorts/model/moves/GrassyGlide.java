package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class GrassyGlide extends PhysicalMove {

    public GrassyGlide() {
        super("Grassy Glide", 60, 100, Type.GRASS, 32, true, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        if (battle.getTerrain() == Terrain.GRASSY && attacker.isGrounded()) {
            return 1;
        } else {
            return super.getPriority(attacker, defender, battle);
        }
    }
}
