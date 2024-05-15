package org.shorts.model.moves.switchtarget;

import org.shorts.battle.Battle;
import org.shorts.model.moves.PhysicalMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.SuctionCups.SUCTION_CUPS;
import static org.shorts.model.status.VolatileStatusType.ROOTED;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class DragonTail extends PhysicalMove implements SwitchTargetMove {

    public DragonTail() {
        super("Dragon Tail", 60, 90, Type.DRAGON, 16, true, 100);
    }

    @Override
    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        return -6;
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!attacker.hasFainted() && defender.getAbility() != SUCTION_CUPS && !defender.hasVolatileStatus(ROOTED)
            && !defender.hasVolatileStatus(SUBSTITUTE)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        SwitchTargetMove.super.forceTargetToSwitchOut(defender, battle.getOpposingTrainer(attacker));
    }
}
