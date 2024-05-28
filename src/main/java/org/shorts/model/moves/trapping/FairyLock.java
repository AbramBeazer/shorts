package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.StatusMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FairyLock extends StatusMove implements TrappingMove {

    public FairyLock() {
        super("Fairy Lock", -1, Type.FAIRY, 16, false);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (TrappingMove.super.targetIsNotGhost(defender)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        TrappingMove.super.applyCantEscapeStatus(defender);
    }
}
