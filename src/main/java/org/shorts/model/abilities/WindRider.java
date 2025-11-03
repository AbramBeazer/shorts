package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.WindMove;
import org.shorts.model.pokemon.Pokemon;

public class WindRider extends Ability {

    private WindRider() {
        super("Wind Rider");
    }

    public static final WindRider WIND_RIDER = new WindRider();

    @Override
    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (move.isWindMove()) {
            self.changeStat(1, StatEnum.ATK);
            return 0;
        } else {
            return 1;
        }
    }
}
