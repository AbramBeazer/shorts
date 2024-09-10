package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class HeavyLightMetalTests {

    private Pokemon mon;

    @BeforeEach
    void setUp() {
        mon = getDummyPokemon();
    }

    @Test
    void testHeavyMetalDoublesWeight() {
        final double baseWeight = mon.getWeight();
        mon.setAbility(HeavyMetal.HEAVY_METAL);
        assertThat(mon.getWeight()).isEqualTo(baseWeight * 2);
    }

    @Test
    void testLightMetalHalvesWeight() {
        final double baseWeight = mon.getWeight();
        mon.setAbility(LightMetal.LIGHT_METAL);
        assertThat(mon.getWeight()).isEqualTo(baseWeight / 2);
    }
}
