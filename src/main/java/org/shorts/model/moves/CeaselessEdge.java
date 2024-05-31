package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class CeaselessEdge extends Move implements SlicingMove {

    public CeaselessEdge() {
        super("Ceaseless Edge", 65, 90, Type.DARK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, true, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        battle.getOpposingTrainer(user).addSpikes();
    }
}
