package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.WindMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

public class WindPower extends Ability {

    private WindPower() {
        super("Wind Power");
    }

    public static final WindPower WIND_POWER = new WindPower();

    @Override
    public void afterHit(Pokemon self, Pokemon opponent, Battle battle, int previousHP, Move move) {
        if (move instanceof WindMove) {
            System.out.printf("Being hit by %s charged %s with power!%n", move, self);
            self.addVolatileStatus(new VolatileStatus(VolatileStatusType.CHARGED, -1));
        }
    }
}
