package org.shorts.model.moves;

import org.shorts.Main;
import org.shorts.model.Status;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Spore extends Move {

    public Spore() {
        super("Spore", 0, 100, Type.GRASS, MoveGroup.STATUS, 24, false);
    }

    @Override
    public void secondaryEffect(Pokemon attacker, Pokemon defender) {
        if (!defender.getTypes().contains(Type.GRASS)) {
            defender.setSleepCounter(Main.RANDOM.nextInt(3) + 1);
            defender.setStatus(Status.SLEEP);
        }
    }
}
