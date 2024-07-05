package org.shorts.model.moves.switchtarget;

import org.shorts.battle.Battle;
import org.shorts.model.moves.AffectedByMagicBounce;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Soundproof.SOUNDPROOF;
import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;

public class Roar extends SwitchTargetMove implements AffectedByMagicBounce {

    public Roar() {
        super("Roar", 0, -1, Type.NORMAL, Category.STATUS, Range.SINGLE_ADJACENT_ANY, 32, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Should fail if target is protected by Crafty Shield.
        if (target.getAbility() != SOUNDPROOF && !target.hasVolatileStatus(SEMI_INVULNERABLE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }
}
