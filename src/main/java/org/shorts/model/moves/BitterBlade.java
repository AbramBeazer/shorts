package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class BitterBlade extends Move implements SlicingMove, HealingMove {

    public BitterBlade() {
        super("Bitter Blade", 90, 100, Type.FIRE, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 16, true, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        super.applySecondaryEffect(user, target, battle);
        //TODO: Implement (maybe I should split healing move into two interfaces, one of which will be DrainingMove)
    }
}
