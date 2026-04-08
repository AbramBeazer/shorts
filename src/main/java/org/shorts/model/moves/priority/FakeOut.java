package org.shorts.model.moves.priority;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.*;

public class FakeOut extends Move implements GetsSheerForceBoost {

    public FakeOut() {
        super("Fake Out", 40, 100, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 16, true, 100);
    }

    @Override
    public boolean canBeUsed(Pokemon user, List<Pokemon> targets, Battle battle) {
        return user.getTurnsInBattle() == 1;
    }

    @Override
    public void executeOnTarget(Pokemon user, Pokemon target, Battle battle) {
        if (canBeUsed(user, List.of(target), battle)) {
            super.executeOnTarget(user, target, battle);
        }
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (FLINCH.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(new VolatileStatus(FLINCH, 1));
    }
}
