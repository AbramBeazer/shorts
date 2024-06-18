package org.shorts.model.abilities.elementabsorb;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Ability;
import org.shorts.model.abilities.IgnorableAbility;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class ElementAbsorbingAbility extends Ability implements IgnorableAbility {

    private Type type;

    private ElementAbsorbingAbility(String name, Type type) {
        super(name);
        this.type = type;
    }

    public static final ElementAbsorbingAbility WATER_ABSORB = new ElementAbsorbingAbility("Water Absorb", Type.WATER);
    public static final ElementAbsorbingAbility VOLT_ABSORB = new ElementAbsorbingAbility("Volt Absorb", Type.ELECTRIC);
    public static final ElementAbsorbingAbility EARTH_EATER = new ElementAbsorbingAbility("Earth Eater", Type.GROUND);

    @Override
    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (move.getType().equals(type) && !self.hasVolatileStatus(VolatileStatusType.PROTECTED)
            && !self.hasVolatileStatus(VolatileStatusType.ABILITY_IGNORED)) {
            self.heal(self.getMaxHP() / 4);
            return 0;
        }
        return 1;
    }
}
