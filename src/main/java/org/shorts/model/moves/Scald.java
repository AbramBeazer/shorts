package org.shorts.model.moves;

import org.shorts.model.Status;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.Main.RANDOM;

public class Scald extends Move {

    public Scald() {
        super("Scald", 80, 100, Type.WATER, MoveGroup.SPECIAL, 24, false);
    }

    @Override
    public void secondaryEffect(Pokemon attacker, Pokemon defender) {
        if (RANDOM.nextInt(10) < 3) {
            defender.setStatus(Status.BURN);
        }
    }
}
