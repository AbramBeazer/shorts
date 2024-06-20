package org.shorts.model.items;

import org.shorts.model.pokemon.Pokemon;

public class AssaultVest extends HeldItem {

    private static final double ASSAULT_VEST_MULTIPLIER = 1.5;

    private AssaultVest() {
        super("Assault Vest", 80);
    }

    public static final AssaultVest ASSAULT_VEST = new AssaultVest();

    @Override
    public double onCalculateSpecialDefense(Pokemon self) {
        return ASSAULT_VEST_MULTIPLIER;
    }
}
