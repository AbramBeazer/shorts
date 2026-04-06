package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class CloseCombat extends Move {

    public CloseCombat() {
        super("Close Combat", 120, 100, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL, 8, true, 100);
    }

    @Override
    public void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.changeStat(battle, user, -1, StatEnum.DEF, StatEnum.SPDEF);
    }

}
