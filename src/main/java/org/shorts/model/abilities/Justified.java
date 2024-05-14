package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class Justified extends Ability {

    private Justified() {
        super("Justified");
    }

    public static final Justified JUSTIFIED = new Justified();

    @Override
    public void afterHit(Pokemon self, Pokemon opponent, Battle battle, int previousHP, Move move) {
        //TODO: Make sure the logic for hitting/damaging/breaking a sub happens before this.
        if (move.getType() == Type.DARK && !opponent.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)) {
            self.changeAttack(1);
        }
    }
}
