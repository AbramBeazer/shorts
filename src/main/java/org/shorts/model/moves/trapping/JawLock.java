package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

public class JawLock extends TrappingMove {

    public JawLock() {
        super("Jaw Lock", 80, 100, Type.DARK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        super.applySecondaryEffect(attacker, defender, battle);
        attacker.addVolatileStatus(VolatileStatus.CANT_ESCAPE);
    }
}
