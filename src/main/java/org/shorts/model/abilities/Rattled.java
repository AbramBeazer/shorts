package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Rattled extends Ability {

    private Rattled() {
        super("Rattled");
    }

    public static final Rattled RATTLED = new Rattled();

    @Override
    public void afterHit(Pokemon self, Pokemon opponent, Battle battle, int previousHP, Move move) {
        if (move.getType() == Type.BUG || move.getType() == Type.GHOST || move.getType() == Type.DARK) {
            self.changeSpeed(1);
        }
    }
}
