package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class TeraBlast extends Move {

    public TeraBlast() {
        super("Tera Blast", 80, 100, Type.NORMAL, Category.SPECIAL, Range.NORMAL, 16, false, 0);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (getType() instanceof Type.StellarType) {
            user.changeStat(-1, StatEnum.ATK);
            user.changeStat(-1, StatEnum.SPATK);
            user.afterDrop(target, battle);
        }
    }
}
