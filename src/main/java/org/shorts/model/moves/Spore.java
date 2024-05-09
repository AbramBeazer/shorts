package org.shorts.model.moves;

import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

public class Spore extends StatusMove {

    public Spore() {
        super("Spore", 0, Type.GRASS, 24, false);
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (defender.getTypes().contains(Type.GRASS)) {
            System.out.println("It doesn't affect " + defender.getNickname());
        } else {
            defender.setStatus(new Status(Status.StatusType.SLEEP, Main.RANDOM.nextInt(3) + 1));
        }
    }

}
