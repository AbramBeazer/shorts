package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class ShellSmash extends Move {

    public ShellSmash() {
        super("Shell Smash", 0, -1, Type.NORMAL, Category.STATUS, Range.SELF, 24, false, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.changeStat(battle, user, -1, StatEnum.DEF, StatEnum.SPDEF);
        user.changeStat(battle, user, 2, StatEnum.ATK, StatEnum.SPATK, StatEnum.SPEED);
    }
}
