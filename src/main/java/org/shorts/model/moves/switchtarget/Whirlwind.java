package org.shorts.model.moves.switchtarget;

import org.shorts.battle.Battle;
import org.shorts.model.moves.AffectedByMagicBounce;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.moves.WindMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;

public class Whirlwind extends SwitchTargetMove implements AffectedByMagicBounce, WindMove {

    public Whirlwind() {
        super("Whirlwind", 0, -1, Type.NORMAL, Move.Category.STATUS, Range.NORMAL, 32, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Should fail if target is protected by Crafty Shield.
        if (!target.hasVolatileStatus(SEMI_INVULNERABLE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

}
