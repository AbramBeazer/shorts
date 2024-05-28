package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Chloroblast extends RecoilAttack {

    public Chloroblast() {
        super("Chlorolast", 150, 95, Type.GRASS, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 8, false, 0, 0.5);
    }

    @Override
    public void inflictRecoil(Pokemon user, int damageDealt) {
        user.takeDamage((int) Math.ceil(user.getMaxHP() * this.recoilPercentage));
    }
}
