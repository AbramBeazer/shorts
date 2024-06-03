package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.BallBombMove;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;

public class Bulletproof extends Ability implements IgnorableAbility {

    private Bulletproof() {
        super("Bulletproof");
    }

    public static final Bulletproof BULLETPROOF = new Bulletproof();

    @Override
    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (move instanceof BallBombMove && !self.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            && !self.hasVolatileStatus(VolatileStatusType.ABILITY_IGNORED)) {
            return 0;
        } else {
            return 1;
        }
    }
}
