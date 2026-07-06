package org.shorts.model.items;

import org.shorts.model.pokemon.Pokemon;

public class ThickClub extends HeldItem {

    private ThickClub() {
        super("Thick Club", 90);
    }

    public static final ThickClub THICK_CLUB = new ThickClub();

    @Override
    public double onCalculateAttack(Pokemon self) {
        return self.getPokedexEntry().getPokedexNo() == 104 || self.getPokedexEntry().getPokedexNo() == 105 ? 2 : 1;
    }
}
