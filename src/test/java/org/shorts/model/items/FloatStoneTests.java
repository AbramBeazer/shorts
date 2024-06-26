package org.shorts.model.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class FloatStoneTests {

    private Pokemon mon;

    @BeforeEach
    void setUp() {
        mon = getDummyPokemon();
    }

    @Test
    void testLightMetalHalvesWeight() {
        final double baseWeight = mon.getWeight();
        mon.setHeldItem(FloatStone.FLOAT_STONE);
        assertThat(mon.getWeight()).isEqualTo(baseWeight / 2);
    }
}
