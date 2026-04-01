package org.shorts.model.moves;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class BeakBlast extends Move implements BallBombMove {

    public BeakBlast() {
        super("Beak Blast", 100, 100, Type.FLYING, Category.PHYSICAL, Range.NORMAL, 24, false, 0);
    }

    @Override
    public int getBasePriority(Pokemon attacker, Battle battle) {
        return -3;
    }

    @Override
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        user.removeVolatileStatus(VolatileStatusType.BEAK_BLAST);
        super.execute(user, targets, battle);
    }
}
