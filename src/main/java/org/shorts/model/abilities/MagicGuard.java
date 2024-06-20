package org.shorts.model.abilities;

public class MagicGuard extends Ability implements SandImmuneAbility, HailImmuneAbility {

    private MagicGuard() {
        super("Magic Guard");
    }

    public static final MagicGuard MAGIC_GUARD = new MagicGuard();
}
