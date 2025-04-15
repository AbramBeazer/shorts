package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class SuperFang extends Move {

    public SuperFang() {
        super("Super Fang", -1, 90, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 16, true, 0);
    }

    @Override
    protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
        return Math.max(target.getCurrentHP() / 2, 1);
    }
}
