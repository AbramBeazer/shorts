package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.OCTOLOCKED;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class Octolock extends Move {

    public Octolock() {
        super("Octolock", 0, 100, Type.FIGHTING, Category.STATUS, Range.SINGLE_ADJACENT_ANY, 24, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!defender.hasVolatileStatus(SUBSTITUTE)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.addVolatileStatus(new VolatileStatus(OCTOLOCKED, -1));
    }
}
