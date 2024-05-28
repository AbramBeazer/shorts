package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.PhysicalMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class AnchorShot extends PhysicalMove implements TrappingMove {

    public AnchorShot() {
        super("Anchor Shot", 80, 100, Type.STEEL, 32, true, 100);
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
