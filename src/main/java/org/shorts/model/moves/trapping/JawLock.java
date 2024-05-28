package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.CANT_ESCAPE;
import static org.shorts.model.status.VolatileStatusType.NO_RETREAT;
import static org.shorts.model.status.VolatileStatusType.OCTOLOCKED;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class JawLock extends TrappingMove {

    public JawLock() {
        super("Jaw Lock", 80, 100, Type.DARK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!attacker.hasVolatileStatus(CANT_ESCAPE) && !attacker.hasVolatileStatus(NO_RETREAT)
            && !attacker.hasVolatileStatus(OCTOLOCKED) &&
            !defender.hasVolatileStatus(CANT_ESCAPE) && !defender.hasVolatileStatus(NO_RETREAT)
            && !defender.hasVolatileStatus(OCTOLOCKED) && !defender.hasVolatileStatus(SUBSTITUTE)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        super.applySecondaryEffect(attacker, defender, battle);
        attacker.addVolatileStatus(VolatileStatus.CANT_ESCAPE_INDEFINITE);
    }
}
