package org.shorts.model.pokemon;

import org.shorts.model.abilities.DummyAbility;

public final class PokemonTestUtils {

    private static Pokemon getDummyPokemon(int stats) {
        final Pokemon mon = new Pokemon(3 * stats, 3 * stats, DummyAbility.DUMMY_ABILITY);
        mon.setSpeciesName("dummy -- for testing");
        mon.setAttack(stats);
        mon.setDefense(stats);
        mon.setSpecialAttack(stats);
        mon.setSpecialDefense(stats);
        mon.setSpeed(stats);
        return mon;
    }

    public static Pokemon getDummyPokemon() {
        return getDummyPokemon(100);
    }
}
