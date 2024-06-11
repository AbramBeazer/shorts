package org.shorts.model.abilities;

public class Unnerve extends Ability implements OpponentCantEatBerriesAbility {

    private Unnerve() {
        super("Unnerve");
    }

    public static final Unnerve UNNERVE = new Unnerve();
}
