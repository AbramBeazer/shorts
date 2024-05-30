package org.shorts.model.moves.priority;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.HelpingHandStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class HelpingHandMove extends Move {

    public HelpingHandMove() {
        super("Helping Hand", 0, -1, Type.NORMAL, Category.STATUS, Range.SINGLE_ADJACENT_ALLY, 32, false, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.hasVolatileStatus(VolatileStatusType.HELPING_HAND)) {
            ((HelpingHandStatus) target.getVolatileStatus(VolatileStatusType.HELPING_HAND)).stackHelpingHand();
        }
        target.addVolatileStatus(new HelpingHandStatus());
    }

    @Override
    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        return 5 + super.getPriority(attacker, defender, battle);
    }
}
