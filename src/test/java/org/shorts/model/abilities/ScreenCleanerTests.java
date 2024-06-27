package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class ScreenCleanerTests {

    private Pokemon user;
    private Battle battle;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        battle = new DummyBattle(user, getDummyPokemon());
    }

    @Test
    void testRemovesScreensOnEntry() {
        assertThat(false).isTrue();
    }

    @Test
    void testAbilityDoesNotActivateOnEntryIfNeutralizingGasIsPresent() {
        assertThat(false).isTrue();
    }
}
