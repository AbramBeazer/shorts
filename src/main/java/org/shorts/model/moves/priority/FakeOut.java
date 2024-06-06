package org.shorts.model.moves.priority;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.FLINCH;

public class FakeOut extends Move {

    public FakeOut() {
        super("Fake Out", 40, 100, Type.NORMAL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 100);
    }

    @Override
    public void executeMove(Pokemon user, Pokemon target, Battle battle) {
        if (user.getTurnsInBattle() == 1) {
            super.executeMove(user, target, battle);
        }
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (FLINCH.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(new VolatileStatus(FLINCH, 1));
    }
}
