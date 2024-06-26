package org.shorts.model.abilities;

public class Contrary extends Ability implements IgnorableAbility {

    private Contrary() {
        super("Contrary");
    }

    public static final Contrary CONTRARY = new Contrary();
}
