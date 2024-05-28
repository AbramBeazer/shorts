package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FairyLock extends Move {

    public FairyLock() {
        super("Fairy Lock", 0, -1, Type.FAIRY, Category.STATUS, Range.BOTH_SIDES, 16, false, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        //TODO: Is this blocked by Substitute?
        //TODO: Does this stack? Can you keep spamming it? Does it mean just the turn in which FairyLock is used, or the turn afterward?
        battle.setFairyLockTurns(2);
    }
}
