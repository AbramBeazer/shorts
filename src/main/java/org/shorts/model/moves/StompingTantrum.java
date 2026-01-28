package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class StompingTantrum extends Move {

    public static final int MULTIPLIER = 2;

    public StompingTantrum() {
        super("Stomping Tantrum", 75, 100, Type.GROUND, Move.Category.PHYSICAL, Range.NORMAL, 16, true, 0);
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        double power = super.getPower(user, target, battle);
        return user.isLastMoveFailed() ? MULTIPLIER * power : power;
    }
}
