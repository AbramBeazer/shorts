package org.shorts.model.moves;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class FocusPunch extends Move implements PunchingMove {

    public FocusPunch() {
        super("Focus Punch", 150, 100, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL, 32, true, 0);
    }

    @Override
    public int getBasePriority(Pokemon attacker, Battle battle) {
        return -3;
    }

    @Override
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        if (user.hasVolatileStatus(VolatileStatusType.FOCUS_PUNCH)) {
            user.removeVolatileStatus(VolatileStatusType.FOCUS_PUNCH);
            super.execute(user, targets, battle);
        } else {
            System.out.printf("%s lost its focus and couldn't move!", user);
            user.setLastMoveFailed(true);
        }
    }
}
