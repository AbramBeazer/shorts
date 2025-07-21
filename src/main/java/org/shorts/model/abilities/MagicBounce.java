package org.shorts.model.abilities;

import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class MagicBounce extends Ability implements IgnorableAbility {

    private MagicBounce() {
        super("Magic Bounce");
    }

    public static void printMessage(Pokemon bouncer, Move move) {
        System.out.println(bouncer.toString() + "'s Magic Bounce reflected the " + move.getName() + "!");
    }

    public static final MagicBounce MAGIC_BOUNCE = new MagicBounce();
}


