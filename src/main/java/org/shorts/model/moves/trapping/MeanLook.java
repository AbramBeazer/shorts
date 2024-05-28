package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.StatusMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class MeanLook extends StatusMove implements TrappingMove {

    public MeanLook() {
        super("Mean Look", -1, Type.NORMAL, 8, false);
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
