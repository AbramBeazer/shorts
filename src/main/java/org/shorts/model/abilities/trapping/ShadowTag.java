package org.shorts.model.abilities.trapping;

import org.shorts.model.abilities.Ability;
import org.shorts.model.moves.Range;

public class ShadowTag extends Ability {

    private ShadowTag() {
        super("Shadow Tag", Range.ALL_ADJACENT_OPPONENTS);
    }

    public static final ShadowTag SHADOW_TAG = new ShadowTag();
}
