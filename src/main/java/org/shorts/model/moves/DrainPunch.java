package org.shorts.model.moves;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class DrainPunch extends Move implements PunchingMove, HealingMove, DrainingMove {
    public DrainPunch() {
        super("Drain Punch", 75, 100, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL, 16, true, 100);
    }

    @Override
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        if (!user.hasVolatileStatus(VolatileStatusType.HEAL_BLOCKED)) {
            super.execute(user, targets, battle);
        }
    }

    //TODO: Implement DrainingMove
}
