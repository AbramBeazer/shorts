package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class DrySkin extends Ability implements IgnorableAbility {

    private DrySkin() {
        super("Dry Skin");
    }

    public static final DrySkin DRY_SKIN = new DrySkin();

    @Override
    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (move.getType().equals(Type.FIRE)) {
            return 1.25;
        } else if (move.getType().equals(Type.WATER)) {
            self.heal(self.getMaxHP() / 4);
            return 0;
        }
        return 1;
    }

}
