package org.shorts.model.moves;

import org.shorts.Main;
import org.shorts.model.Status;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Thunder extends Move {

    public Thunder() {
        super("Thunder", 120, 70, Type.ELECTRIC, MoveGroup.SPECIAL, 16, false);
    }

    @Override
    public void secondaryEffect(Pokemon attacker, Pokemon defender) {
        if (!defender.getTypes().contains(Type.ELECTRIC)) {
            if (Main.RANDOM.nextInt(10) < 3) {
                defender.setStatus(Status.PARALYZE);
            }
        }
    }
}
