package org.shorts.model.items;

import org.shorts.model.pokemon.Pokemon;

public class MegaStone extends HeldItem {

    private String species;

    private MegaStone(String name, String species) {
        super(name);
        this.species = species;
    }

    public boolean isCorrectPokemon(Pokemon pokemon) {
        return pokemon.getPokedexEntry().getSpeciesName().equals(species);
    }

    public static final MegaStone TYRANITARITE = new MegaStone("Tyranitarite", "Tyranitar");
    //TODO: Do I implement these? There's no Mega Evolution in Gen 9.
}
