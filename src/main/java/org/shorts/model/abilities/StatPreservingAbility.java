package org.shorts.model.abilities;

public class StatPreservingAbility extends Ability {

    protected StatPreservingAbility(String name) {
        super(name);
    }

    public static final StatPreservingAbility FULL_METAL_BODY = new StatPreservingAbility("Full Metal Body");

}
