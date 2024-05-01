package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.Sex;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class Rivalry extends Ability {

    private Rivalry() {
        super("Rivalry");
    }

    public static final Rivalry RIVALRY = new Rivalry();

    @Override
    public double onMovePowerCalc(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (self.getSex() == Sex.NONE || opponent.getSex() == Sex.NONE) {
            return 1;
        } else {
            if (self.getSex() == opponent.getSex()) {
                return 1.25;
            } else {
                return 0.75;
            }
        }
    }
}
