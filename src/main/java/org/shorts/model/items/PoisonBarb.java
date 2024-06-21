package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class PoisonBarb extends HeldItem {

    private PoisonBarb() {
        super("Poison Barb", 70);
    }

    public static final PoisonBarb POISON_BARB = new PoisonBarb();

    @Override
    public double getMovePowerMultipliers(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        return move.getType() == Type.POISON ? 1.2 : 1;
    }
}
