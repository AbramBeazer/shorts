package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Levitate extends Ability {

    private Levitate() {
        super("Levitate");
    }

    @Override
    public void beforeHit(Pokemon self, Pokemon opponent, Battle battle, Integer damage, Type moveType) {
        if (moveType.equals(Type.GROUND)) {
            damage = 0;
            //TODO: LOGGER.info("It didn't affect {}", target.getLead().getNickname());
        }
    }

    public static final Levitate LEVITATE = new Levitate();
}
