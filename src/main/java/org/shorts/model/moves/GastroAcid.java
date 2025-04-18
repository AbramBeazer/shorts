package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.UnsuppressableAbility;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

public class GastroAcid extends Move implements AffectedByMagicBounce {

    public GastroAcid() {
        super("Gastro Acid", 0, 100, Type.POISON, Category.STATUS, Range.NORMAL, 16, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (!(target.getAbility() instanceof UnsuppressableAbility)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(VolatileStatus.ABILITY_SUPPRESSED);
    }
}
