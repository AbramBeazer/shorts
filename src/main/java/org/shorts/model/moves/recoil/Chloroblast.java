package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.MagicGuard.MAGIC_GUARD;

public class Chloroblast extends RecoilAttack {

    public Chloroblast() {
        super("Chlorolast", 150, 95, Type.GRASS, Category.SPECIAL, Range.NORMAL, 8, false, 0, 0.5);
    }

    @Override
    public void inflictRecoil(Pokemon user, int damageDealt) {
        if (user.getAbility() != MAGIC_GUARD) {
            user.takeDamage(
                (int) Math.ceil(user.getMaxHP() * this.recoilPercentage),
                String.format("%s was damaged by the recoil!", user));
        }
    }
}
