package org.shorts.model.abilities;

public class StickyHold extends Ability implements IgnorableAbility {

    private StickyHold() {
        super("Sticky Hold");
    }

    public static final StickyHold STICKY_HOLD = new StickyHold();
}
