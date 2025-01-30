package org.shorts.model.moves.priority.plusOne;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.moves.AffectedByMagicBounce;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class BabyDollEyes extends Move implements AffectedByMagicBounce {

    public BabyDollEyes() {
        super("Baby-Doll Eyes", 0, -1, Type.FAIRY, Category.STATUS, Range.NORMAL_SINGLE_ADJACENT_ANY, 48, false, 100);
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        return 1;
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.ATK) && !target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(-1, StatEnum.ATK);
    }
}
