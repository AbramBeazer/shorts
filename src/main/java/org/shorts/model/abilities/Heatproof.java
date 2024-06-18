package org.shorts.model.abilities;

import java.util.Set;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.status.StatusType.BURN;

public class Heatproof extends StatusImmuneAbility {

    private Heatproof() {
        super("Heatproof", Set.of(BURN));
    }

    public static final Heatproof HEATPROOF = new Heatproof();

    @Override
    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (move.getType().equals(Type.FIRE) && !self.hasVolatileStatus(VolatileStatusType.ABILITY_IGNORED)) {
            return .5;
        }
        return 1;
    }

    //TODO: Add tests
}
