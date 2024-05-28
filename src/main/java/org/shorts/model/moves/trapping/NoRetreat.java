package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.CANT_ESCAPE;
import static org.shorts.model.status.VolatileStatusType.NO_RETREAT;

public class NoRetreat extends Move {

    public NoRetreat() {
        super("No Retreat", 0, -1, Type.FIGHTING, Category.STATUS, Range.SELF, 8, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!attacker.hasVolatileStatus(NO_RETREAT)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        attacker.changeAttack(1);
        attacker.changeDefense(1);
        attacker.changeSpecialAttack(1);
        attacker.changeSpecialDefense(1);
        attacker.changeSpeed(1);
        if (!attacker.hasVolatileStatus(CANT_ESCAPE)) {
            attacker.addVolatileStatus(new VolatileStatus(NO_RETREAT, -1));
        }
    }
}
