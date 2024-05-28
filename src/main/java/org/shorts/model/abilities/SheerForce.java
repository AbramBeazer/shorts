package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.trapping.ThousandWaves;
import org.shorts.model.pokemon.Pokemon;

public class SheerForce extends Ability {

    private SheerForce() {
        super("Sheer Force");
    }

    //TODO: Figure out how Life Orb is going to work with this.
    public static final SheerForce SHEER_FORCE = new SheerForce();
    //TODO: Implement methods

    @Override
    public double getMovePowerMultipliers(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (move instanceof ThousandWaves || move.getSecondaryEffectChance() <= 0) {
            return 1;
        } else {
            return 5325d / 4096d;
        }
    }
}
