package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Levitate extends Ability implements IgnorableAbility {

    private Levitate() {
        super("Levitate");
    }

    @Override
    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (move.getType().equals(Type.GROUND) && !(opponent.getAbility() instanceof NullifyingAbility)) {
            return 0;
        } else {
            return 1;
        }
    }

    public static final Levitate LEVITATE = new Levitate();
}
