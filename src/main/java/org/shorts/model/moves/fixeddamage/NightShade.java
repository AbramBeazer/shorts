package org.shorts.model.moves.fixeddamage;

import org.shorts.battle.Battle;
import org.shorts.model.moves.SpecialMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class NightShade extends SpecialMove {

    public NightShade() {
        super("Night Shade", 0, 100, Type.GHOST, 24, false, 0);
    }

    @Override
    protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
        if (Type.getTypeMultiplier(this.getType(), target.getTypes()) == 0) {
            return 0;
        } else {
            return user.getLevel();
        }
    }
}
