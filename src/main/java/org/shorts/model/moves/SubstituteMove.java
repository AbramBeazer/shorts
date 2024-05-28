package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class SubstituteMove extends Move {

    private int subHP;

    public SubstituteMove() {
        super("Substitute", 0, -1, Type.NORMAL, Category.STATUS, Range.SELF, 16, true, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        subHP = (int) Math.ceil((double) attacker.getMaxHP() / 4);
        if (!attacker.hasVolatileStatus(SUBSTITUTE) && attacker.getCurrentHP() > subHP) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        attacker.setCurrentHP(attacker.getCurrentHP() - subHP);
        attacker.addVolatileStatus(new SubstituteStatus(subHP));
    }
}
