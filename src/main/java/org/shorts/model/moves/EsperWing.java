package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class EsperWing extends Move implements HighCritChanceMove {

    public EsperWing() {
        super("Esper Wing", 80, 100, Type.PSYCHIC, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 16, false, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.changeStat(1, StatEnum.SPEED);
    }
}
