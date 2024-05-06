package org.shorts.model.abilities;

public class NullifyingAbility extends Ability {

    private NullifyingAbility(String name) {
        super(name);
    }

    public static final NullifyingAbility MOLD_BREAKER = new NullifyingAbility("Mold Breaker");
    public static final NullifyingAbility TERAVOLT = new NullifyingAbility("Teravolt");
    public static final NullifyingAbility TURBOBLAZE = new NullifyingAbility("Turboblaze");

}
