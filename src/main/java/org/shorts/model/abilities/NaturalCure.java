package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;

public class NaturalCure extends Ability {

    private NaturalCure() {
        super("Natural Cure");
    }

    public static final NaturalCure NATURAL_CURE = new NaturalCure();

    @Override
    public void beforeSwitchOut(Pokemon self, Pokemon opponent, Battle battle) {
        self.setStatus(Status.NONE);
    }
}

