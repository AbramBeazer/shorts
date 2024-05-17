package org.shorts.model.pokemon;

import org.shorts.model.abilities.DummyAbility;

public final class PokemonTestUtils {

    public static Pokemon getDummyPokemon() {
        final Pokemon mon = new Pokemon(100, 100, DummyAbility.DUMMY_ABILITY);
        mon.setSpeciesName("dummy -- for testing");
        return mon;
    }
}
