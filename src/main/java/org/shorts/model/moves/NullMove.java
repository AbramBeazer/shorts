package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class NullMove extends Move {

    private NullMove() {
        super("", 0, 0, Type.NORMAL, 0, false, Integer.MIN_VALUE);
    }

    public static final NullMove NULL_MOVE = new NullMove();

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {

    }
}
