package org.shorts.model.pokemon;

import org.shorts.model.abilities.DummyAbility;

public final class PokemonTestUtils {

    public static Pokemon getDummyPokemon() {
        final Pokemon mon = new Pokemon(100, 100, DummyAbility.DUMMY_ABILITY);
        mon.setSpeciesName("dummy -- for testing");
        mon.setAttack(100);
        mon.setDefense(100);
        mon.setSpecialAttack(100);
        mon.setSpecialDefense(100);
        mon.setSpeed(100);
        return mon;
    }
}
