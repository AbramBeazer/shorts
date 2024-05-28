package org.shorts.model.moves.trapping.binding;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.BoundStatus;
import org.shorts.model.types.Type;

import static org.shorts.Main.RANDOM;
import static org.shorts.model.types.Type.GHOST;

public class Whirlpool extends Move {

    public Whirlpool() {
        super("Whirlpool", 35, 85, Type.WATER, 24, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!defender.getTypes().contains(GHOST)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.addVolatileStatus(new BoundStatus(RANDOM.nextInt(1) + 4, this, 0.125));
    }
}
