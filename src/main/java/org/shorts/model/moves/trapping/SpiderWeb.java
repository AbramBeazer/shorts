package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.StatusMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class SpiderWeb extends StatusMove implements TrappingMove {

    public SpiderWeb() {
        super("Spider Web", -1, Type.BUG, 16, false);
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
