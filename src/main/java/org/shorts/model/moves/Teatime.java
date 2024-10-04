package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.items.berries.Berry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class Teatime extends Move {

    public Teatime() {
        super("Teatime", 0, -1, Type.NORMAL, Category.STATUS, Range.ALL, 16, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        //TODO: If Teatime has its type changed by Electrify or Plasma Fists, Pokémon with Volt Absorb, Lightning Rod or Motor Drive will not use up their Berries but will instead gain the benefit of the Ability regardless of whether they are holding a Berry or not.
        if (!target.hasFainted() && !target.hasVolatileStatus(VolatileStatusType.SEMI_INVULNERABLE)
            && target.getHeldItem() instanceof Berry) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        ((Berry) target.getHeldItem()).eatOwnBerry(target);
    }
}
