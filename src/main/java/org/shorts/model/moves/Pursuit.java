package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Pursuit extends Move {

    public static final double PURSUIT_MULTIPLIER = 2.0;

    public Pursuit() {
        super("Pursuit", 40, 100, Type.DARK, Category.PHYSICAL, Range.NORMAL, 32, true, 0);
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Figure out how to tell if user is switching and add to that conditional
        if (battle.getCorrespondingTrainer(user) != battle.getCorrespondingTrainer(target)
            //&&target.isSwitching()
        ) {
            return PURSUIT_MULTIPLIER * super.getPowerMultipliers(user, target, battle);
        }
        return super.getPowerMultipliers(user, target, battle);
    }
}
