package org.shorts.model.moves.switchtarget;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.SuctionCups.SUCTION_CUPS;
import static org.shorts.model.status.VolatileStatusType.ROOTED;

public abstract class SwitchTargetMove extends Move {

    protected SwitchTargetMove(
        String name,
        double power,
        double accuracy,
        Type type,
        Category category,
        Range range,
        int maxPP,
        boolean contact,
        int secondaryEffectChance) {
        super(name, power, accuracy, type, category, range, maxPP, contact, secondaryEffectChance);
    }

    @Override
    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        return -6;
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!attacker.hasFainted() && defender.getAbility() != SUCTION_CUPS && !defender.hasVolatileStatus(ROOTED)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        battle.getOpposingTrainer(attacker).forceRandomSwitch(defender);
    }
}
