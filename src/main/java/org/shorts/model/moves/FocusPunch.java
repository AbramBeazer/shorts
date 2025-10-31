package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FocusPunch extends Move implements PunchingMove {

    public FocusPunch() {
        super("Focus Punch", 150, 100, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL, 32, true, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        return -3;
    }

    //TODO: Implement this and Beak Blast
}
