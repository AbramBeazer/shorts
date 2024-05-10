package org.shorts.model.abilities;

public class StatPreservingIgnorableAbility extends StatPreservingAbility implements IgnorableAbility {

    private StatPreservingIgnorableAbility(String name) {
        super(name);
    }

    public static final StatPreservingIgnorableAbility CLEAR_BODY = new StatPreservingIgnorableAbility("Clear Body");
    public static final StatPreservingIgnorableAbility WHITE_SMOKE = new StatPreservingIgnorableAbility("White Smoke");
}
