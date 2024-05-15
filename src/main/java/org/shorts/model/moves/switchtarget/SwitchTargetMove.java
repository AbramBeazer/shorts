package org.shorts.model.moves.switchtarget;

import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Pokemon;

public interface SwitchTargetMove {

    default void forceTargetToSwitchOut(Pokemon target, Trainer targetTrainer) {
        targetTrainer.forceRandomSwitch(target);
    }
}
