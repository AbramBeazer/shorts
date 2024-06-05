package org.shorts.model.abilities.statpreserving;

import org.shorts.model.StatEnum;

public class PreserveAccuracyIgnoreEvasionAbility extends StatPreservingIgnorableAbility {

    private PreserveAccuracyIgnoreEvasionAbility(String name) {
        super(name, StatEnum.ACCURACY);
    }

    public static final PreserveAccuracyIgnoreEvasionAbility KEEN_EYE = new PreserveAccuracyIgnoreEvasionAbility(
        "Keen Eye");
    public static final PreserveAccuracyIgnoreEvasionAbility ILLUMINATE = new PreserveAccuracyIgnoreEvasionAbility(
        "Illuminate");

}
