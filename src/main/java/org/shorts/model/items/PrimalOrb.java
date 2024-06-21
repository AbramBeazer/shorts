package org.shorts.model.items;

import org.shorts.model.pokemon.Pokemon;

public class PrimalOrb extends HeldItem {

    private final String species;

    private PrimalOrb(String name, String species) {
        super(name, 0);
        this.species = species;
    }

    public static final PrimalOrb RED_ORB = new PrimalOrb("Red Orb", "Groudon");
    public static final PrimalOrb BLUE_ORB = new PrimalOrb("Blue Orb", "Kyogre");

    public boolean isCorrectPokemon(Pokemon pokemon) {
        return pokemon.getPokedexEntry().getSpeciesName().equals(species);
    }
}
