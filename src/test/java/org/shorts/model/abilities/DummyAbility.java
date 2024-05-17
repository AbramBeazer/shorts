package org.shorts.model.abilities;

public class DummyAbility extends Ability {

    private DummyAbility() {
        super("Dummy Ability -- for testing");
    }

    public static final DummyAbility DUMMY_ABILITY = new DummyAbility();
}
