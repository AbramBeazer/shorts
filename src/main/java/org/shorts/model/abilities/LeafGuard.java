package org.shorts.model.abilities;

import java.util.List;

import static org.shorts.model.status.Status.StatusType.BURN;
import static org.shorts.model.status.Status.StatusType.FREEZE;
import static org.shorts.model.status.Status.StatusType.PARALYZE;
import static org.shorts.model.status.Status.StatusType.POISON;
import static org.shorts.model.status.Status.StatusType.SLEEP;
import static org.shorts.model.status.Status.StatusType.TOXIC_POISON;
import static org.shorts.model.status.volatilestatus.VolatileStatus.VolatileStatusType.DROWSY;

public class LeafGuard extends StatusImmuneAbility implements IgnorableAbility {

    private LeafGuard() {
        super("Leaf Guard", List.of(DROWSY, SLEEP, FREEZE, BURN, PARALYZE, POISON, TOXIC_POISON));
    }

    public static final LeafGuard LEAF_GUARD = new LeafGuard();

    //TODO: Implement this
    //    @Override public void whateverMethodTheParentClassOverrides(Pokemon self, Pokemon opponent, Battle battle) {
    //        if(battle.getWeather() == Weather.SUN || Weather.EXTREME_SUN) {
    //            super.whateverMethodTheParentClassOverrides(self, opponent, battle);
    //        }
    //    }
}
