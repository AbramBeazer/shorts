package org.shorts.model.abilities;

public class ThickFat extends Ability implements IgnorableAbility {

    private ThickFat() {
        super("Thick Fat");
    }

    public static final ThickFat THICK_FAT = new ThickFat();
}
