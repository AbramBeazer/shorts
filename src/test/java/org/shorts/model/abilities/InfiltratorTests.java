package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class InfiltratorTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
    }

    @Test
    void testPassesThroughReflectWithPhysicalMove() {
        assertThat(false).isTrue();
    }

    @Test
    void testPassesThroughLightScreenWithSpecialMove() {
        assertThat(false).isTrue();
    }

    @Test
    void testPassesThroughAuroraVeilWithPhysicalMove() {
        assertThat(false).isTrue();
    }

    @Test
    void testPassesThroughAuroraVeilWithSpecialMove() {
        assertThat(false).isTrue();
    }

    @Test
    void testIgnoresSafeguard() {
        assertThat(false).isTrue();
    }

    @Test
    void testIgnoresMist() {
        assertThat(false).isTrue();
    }

    @Test
    void testIgnoresSubstitute() {
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotIgnoreSubstituteIfUsingTransform() {
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotIgnoreSubstituteIfUsingSkyDrop() {
        assertThat(false).isTrue();
    }

}
