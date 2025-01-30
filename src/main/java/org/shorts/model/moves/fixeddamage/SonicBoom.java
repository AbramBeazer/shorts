package org.shorts.model.moves.fixeddamage;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Scrappy.SCRAPPY;

public class SonicBoom extends Move {

    static final int FIXED_DAMAGE = 20;

    public SonicBoom() {
        super("Sonic Boom", 0, 90, Type.NORMAL, Category.SPECIAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 32, false, 0);
    }

    @Override
    protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
        if (Type.getTypeMultiplier(this.getType(), target.getTypes()) == 0 && user.getAbility() != SCRAPPY) {
            return 0;
        } else {
            return FIXED_DAMAGE;
        }
    }
}
