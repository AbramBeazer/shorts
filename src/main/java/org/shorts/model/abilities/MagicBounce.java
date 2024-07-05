package org.shorts.model.abilities;

public class MagicBounce extends Ability implements IgnorableAbility {

    private MagicBounce() {
        super("Magic Bounce");
    }

    public static final MagicBounce MAGIC_BOUNCE = new MagicBounce();
}


