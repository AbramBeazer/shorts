package org.shorts.model.abilities;

import java.util.List;

import org.shorts.model.status.AbstractStatusType;

import static org.shorts.model.status.StatusType.BURN;
import static org.shorts.model.status.StatusType.FREEZE;
import static org.shorts.model.status.StatusType.PARALYZE;
import static org.shorts.model.status.StatusType.POISON;
import static org.shorts.model.status.StatusType.SLEEP;
import static org.shorts.model.status.StatusType.TOXIC_POISON;

public class Comatose extends Ability implements UnsuppressableAbility {

    private static final List<AbstractStatusType> IMMUNITIES = List.of(
        SLEEP,
        FREEZE,
        BURN,
        PARALYZE,
        TOXIC_POISON,
        POISON);

    private Comatose() {
        super("Comatose");
    }

    public static final Comatose COMATOSE = new Comatose();

    //TODO: Implement methods
}
