package org.shorts.model.abilities;

public class SandVeil extends Ability implements SandImmuneAbility {

    private SandVeil() {
        super("Sand Veil");
    }

    public static final SandVeil SAND_VEIL = new SandVeil();
}
