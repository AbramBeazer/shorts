package org.shorts.model.abilities;

public class Overcoat extends Ability implements SandImmuneAbility, HailImmuneAbility {

    private Overcoat() {
        super("Overcoat");
    }

    public static final Overcoat OVERCOAT = new Overcoat();

    //TODO: Implement, if needed (may work just to check for this wherever needed)

}
