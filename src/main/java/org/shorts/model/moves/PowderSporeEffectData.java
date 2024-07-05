package org.shorts.model.moves;

import org.shorts.model.abilities.Ability;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Overcoat.OVERCOAT;
import static org.shorts.model.items.SafetyGoggles.SAFETY_GOGGLES;

public class PowderSporeEffectData {

    private PowderSporeEffect obj;

    public PowderSporeEffectData(PowderSporeEffect obj) {
        //TODO: Maybe make an interface that's a parent to Move, Ability, and PowderSporeEffect?
        if (obj instanceof Ability || obj instanceof Move) {
            this.obj = obj;
        } else {
            throw new IllegalArgumentException("PowderSporeEffect must be either Move or Ability.");
        }
    }

    public boolean canActivate(Pokemon target) {
        return !target.getTypes().contains(Type.GRASS) && target.getHeldItem() != SAFETY_GOGGLES
            && (target.getAbility() != OVERCOAT || target.hasVolatileStatus(VolatileStatusType.ABILITY_IGNORED)
            || target.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED));
    }
}
