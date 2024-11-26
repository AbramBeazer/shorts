package org.shorts.model.moves.floating;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class Wish extends FloatingMove {

    public Wish(Pokemon user, int targetIndex) {
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
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        if (!user.hasVolatileStatus(VolatileStatusType.HEAL_BLOCKED)) {
            super.execute(user, targets, battle);
        }
    }

    @Override
    public void executeOnTarget(Pokemon user, Pokemon target, Battle battle) {
        target.heal(user.getMaxHP() / 2);
    }
}
