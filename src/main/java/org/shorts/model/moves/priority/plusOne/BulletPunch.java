package org.shorts.model.moves.priority.plusOne;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class BulletPunch extends Move {

    public BulletPunch() {
        super("Bullet Punch", 40, 100, Type.STEEL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 48, true, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        return 1;
    }
}
