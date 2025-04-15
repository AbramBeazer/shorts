package org.shorts.model.moves;

import java.util.Set;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.TypeChangeStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.shorts.model.abilities.Multitype.MULTITYPE;
import static org.shorts.model.abilities.RKSSystem.RKS_SYSTEM;
import static org.shorts.model.types.Type.WATER;

public class Soak extends Move {

    public Soak() {
        super("Soak", 0, 100, WATER, Category.STATUS, Range.NORMAL, 32, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (!target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE) && target.getAbility() != MULTITYPE
            && target.getAbility() != RKS_SYSTEM
            //TODO: && !target.isTera() && !(target.getAbility() instanceof Disguise && ((Disguise) target.getAbility()).isIntact())
        ) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(new TypeChangeStatus(VolatileStatusType.TYPE_CHANGE, -1, target.getTypes()));
        target.setTypes(Set.of(WATER));
    }
}
