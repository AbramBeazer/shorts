package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class ThousandWaves extends TrappingMove {

    public ThousandWaves() {
        super("Thousand Waves", 90, 100, Type.GROUND, Category.PHYSICAL, Range.ALL_ADJACENT_OPPONENTS, 16, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        applySecondaryEffect(attacker, defender, battle);
    }

    //Not in Gen 9
}
