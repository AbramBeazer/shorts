package org.shorts.model.moves.trapping.binding;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.BoundStatus;
import org.shorts.model.types.Type;

import static org.shorts.Main.RANDOM;
import static org.shorts.model.types.Type.GHOST;

public class Whirlpool extends Move {

    public Whirlpool() {
        super("Whirlpool", 35, 85, Type.WATER, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 24, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (!target.getTypes().contains(GHOST)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(new BoundStatus(RANDOM.nextInt(1) + 4, this, 0.125));
    }
}
