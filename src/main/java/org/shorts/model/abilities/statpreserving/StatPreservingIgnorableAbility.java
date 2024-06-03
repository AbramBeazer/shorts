package org.shorts.model.abilities.statpreserving;

import org.shorts.model.StatEnum;
import org.shorts.model.abilities.IgnorableAbility;

public class StatPreservingIgnorableAbility extends StatPreservingAbility implements IgnorableAbility {

    protected StatPreservingIgnorableAbility(String name, StatEnum stat) {
        super(name, stat);
    }

    @Override
    public boolean isDropPossible(boolean ignored, boolean suppressed, StatEnum stat) {
        if (ignored) {
            return true;
        } else {
            return super.isDropPossible(ignored, suppressed, stat);
        }
    }

    public static final StatPreservingIgnorableAbility CLEAR_BODY = new StatPreservingIgnorableAbility(
        "Clear Body",
        null);
    public static final StatPreservingIgnorableAbility WHITE_SMOKE = new StatPreservingIgnorableAbility(
        "White Smoke",
        null);

    public static final StatPreservingIgnorableAbility HYPER_CUTTER = new StatPreservingIgnorableAbility(
        "Hyper Cutter",
        StatEnum.ATK);
    public static final StatPreservingIgnorableAbility BIG_PECKS = new StatPreservingIgnorableAbility(
        "Big Pecks",
        StatEnum.DEF);
}
