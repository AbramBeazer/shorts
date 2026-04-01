package org.shorts.model.moves.floating;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class Wish extends FloatingMove {

    public Wish() {
        super(
            "Wish",
            0,
            -1,
            Type.NORMAL,
            Category.STATUS,
            Range.SELF,
            16,
            false,
            100,
            2);
    }

    @Override
    public boolean canBeUsed(Pokemon user, List<Pokemon> targets, Battle battle) {
        return super.canBeUsed(user, targets, battle) && !user.isHealBlocked();
    }

    @Override
    public void executeOnTarget(Pokemon user, Pokemon target, Battle battle) {
        target.heal(user.getMaxHP() / 2);
    }
}
