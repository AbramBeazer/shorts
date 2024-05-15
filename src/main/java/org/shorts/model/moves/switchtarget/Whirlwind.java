package org.shorts.model.moves.switchtarget;

import org.shorts.battle.Battle;
import org.shorts.model.moves.StatusMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.SuctionCups.SUCTION_CUPS;
import static org.shorts.model.status.VolatileStatusType.ROOTED;
import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;

public class Whirlwind extends StatusMove implements SwitchTargetMove {

    public Whirlwind() {
        super("Whirlwind", -1, Type.NORMAL, 32, false);
    }

    @Override
    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        return -6;
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        //TODO: Should fail if target is protected by Crafty Shield.
        if (defender.getAbility() != SUCTION_CUPS
            && !defender.hasVolatileStatus(ROOTED) && !defender.hasVolatileStatus(SEMI_INVULNERABLE)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        SwitchTargetMove.super.forceTargetToSwitchOut(defender, battle.getOpposingTrainer(attacker));
    }
}
