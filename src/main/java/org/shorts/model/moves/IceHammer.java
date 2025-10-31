package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class IceHammer extends Move implements PunchingMove {

    public IceHammer() {
        super("Ice Hammer", 100, 90, Type.ICE, Category.PHYSICAL, Range.NORMAL, 16, true, 100);
    }

    @Override
    public void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.changeStat(-1, StatEnum.SPEED);
        user.afterDrop(target, battle);
    }
}