package org.shorts.model.items;

import org.shorts.model.pokemon.Pokemon;

public class MegaStone extends HeldItem {

    private String species;

    private MegaStone(String name, String species) {
        super(name);
        this.species = species;
    }

    public boolean isCorrectPokemon(Pokemon pokemon) {
        return pokemon.getSpeciesName().equals(species);
    }
}
