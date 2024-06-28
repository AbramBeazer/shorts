package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.NeutralizingGas.NEUTRALIZING_GAS;
import static org.shorts.model.abilities.ScreenCleaner.SCREEN_CLEANER;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class ScreenCleanerTests {

    private Pokemon user;
    private Battle battle;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        user.setAbility(SCREEN_CLEANER);
        battle = new DummyBattle(user, getDummyPokemon());
    }

    @Test
    void testRemovesScreensOnEntry() {
        final Trainer player = battle.getCorrespondingTrainer(user);
        final Trainer opponent = battle.getOpposingTrainer(user);

        player.setAuroraVeilTurns(5);
        player.setReflectTurns(5);
        player.setLightScreenTurns(5);

        opponent.setAuroraVeilTurns(5);
        opponent.setReflectTurns(5);
        opponent.setLightScreenTurns(5);

        user.afterEntry(battle);

        assertThat(player.getAuroraVeilTurns()).isZero();
        assertThat(player.getReflectTurns()).isZero();
        assertThat(player.getLightScreenTurns()).isZero();

        assertThat(opponent.getAuroraVeilTurns()).isZero();
        assertThat(opponent.getReflectTurns()).isZero();
        assertThat(opponent.getLightScreenTurns()).isZero();
    }

    @Test
    void testAbilityDoesNotActivateOnEntryIfNeutralizingGasIsPresent() {
        final Trainer player = battle.getCorrespondingTrainer(user);
        final Trainer opponent = battle.getOpposingTrainer(user);

        player.setAuroraVeilTurns(5);
        player.setReflectTurns(5);
        player.setLightScreenTurns(5);

        opponent.setAuroraVeilTurns(5);
        opponent.setReflectTurns(5);
        opponent.setLightScreenTurns(5);

        opponent.getActivePokemon().get(0).setAbility(NEUTRALIZING_GAS);
        opponent.getActivePokemon().get(0).afterEntry(battle);
        user.afterEntry(battle);

        assertThat(player.getAuroraVeilTurns()).isEqualTo(5);
        assertThat(player.getReflectTurns()).isEqualTo(5);
        assertThat(player.getLightScreenTurns()).isEqualTo(5);

        assertThat(opponent.getAuroraVeilTurns()).isEqualTo(5);
        assertThat(opponent.getReflectTurns()).isEqualTo(5);
        assertThat(opponent.getLightScreenTurns()).isEqualTo(5);
    }
}
