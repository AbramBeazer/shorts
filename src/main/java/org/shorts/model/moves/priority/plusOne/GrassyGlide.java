package org.shorts.model.moves.priority.plusOne;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class GrassyGlide extends Move {

    public GrassyGlide() {
        super("Grassy Glide", 55, 100, Type.GRASS, Category.PHYSICAL, Range.NORMAL, 32, true, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        if (battle.getTerrain() == Terrain.GRASSY && attacker.isGrounded()) {
            return 1;
        } else {
            return 0;
        }
    }
}
