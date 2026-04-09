package org.shorts.model.abilities;

import java.util.Optional;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;

public class Guts extends Ability {

    static final double GUTS_MULTIPLIER = 1.5;

    private Guts() {
        super("Guts");
    }

    @Override
    public double onCalculateAttack(Pokemon self) {
        final StatusType statusType = Optional.ofNullable(self.getStatus())
            .map(Status::getType)
            .orElse(StatusType.NONE);
        if (statusType.equals(StatusType.NONE) || statusType.equals(StatusType.FREEZE)) {
            return 1;
        } else {
            return GUTS_MULTIPLIER;
        }
    }

    public static final Guts GUTS = new Guts();
}
