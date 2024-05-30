package org.shorts.model.moves.priority.plusOne;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class BabyDollEyes extends Move {

    public BabyDollEyes() {
        super("Baby-Doll Eyes", 0, -1, Type.FAIRY, Category.STATUS, Range.SINGLE_ADJACENT_ANY, 48, false, 100);
    }

    @Override
    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        return 1 + super.getPriority(attacker, defender, battle);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeAttack(-1);
    }
}
