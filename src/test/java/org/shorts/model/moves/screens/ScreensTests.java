package org.shorts.model.moves.screens;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Trainer;
import org.shorts.battle.Weather;
import org.shorts.model.moves.Earthquake;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Surf;
import org.shorts.model.moves.Tackle;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.items.LightClay.LIGHT_CLAY;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class ScreensTests {

    private Pokemon screenSetter;
    private Pokemon opponent;
    private Battle battle;
    private Trainer player;

    @BeforeEach
    void setUp() {
        screenSetter = getDummyPokemon();
        opponent = getDummyPokemon();
        battle = new DummyBattle(screenSetter, opponent);
        player = battle.getCorrespondingTrainer(screenSetter);
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
    }

    @Test
    void testReflectSetsScreen() {
        final Move move = new Reflect();

        move.execute(screenSetter, player.getActivePokemon(), battle);
        assertThat(player.getReflectTurns()).isEqualTo(5);
        assertThat(player.getLightScreenTurns()).isZero();
        assertThat(player.getAuroraVeilTurns()).isZero();
    }

    @Test
    void testLightScreenSetsScreen() {
        final Move move = new LightScreen();

        move.execute(screenSetter, player.getActivePokemon(), battle);
        assertThat(player.getReflectTurns()).isZero();
        assertThat(player.getLightScreenTurns()).isEqualTo(5);
        assertThat(player.getAuroraVeilTurns()).isZero();

    }

    @Test
    void testAuroraVeilSetsScreenInHail() {
        battle.setWeather(Weather.HAIL, 5);
        final Move move = new AuroraVeil();

        move.execute(screenSetter, player.getActivePokemon(), battle);
        assertThat(player.getReflectTurns()).isZero();
        assertThat(player.getLightScreenTurns()).isZero();
        assertThat(player.getAuroraVeilTurns()).isEqualTo(5);
    }

    @Test
    void testAuroraVeilSetsScreenInSnow() {
        battle.setWeather(Weather.SNOW, 5);
        final Move move = new AuroraVeil();

        move.execute(screenSetter, player.getActivePokemon(), battle);
        assertThat(player.getReflectTurns()).isZero();
        assertThat(player.getLightScreenTurns()).isZero();
        assertThat(player.getAuroraVeilTurns()).isEqualTo(5);
    }

    @Test
    void testAuroraVeilFailsWithoutSnowOrHail() {
        final Move move = new AuroraVeil();

        move.execute(screenSetter, player.getActivePokemon(), battle);
        assertThat(player.getReflectTurns()).isZero();
        assertThat(player.getLightScreenTurns()).isZero();
        assertThat(player.getAuroraVeilTurns()).isZero();
    }

    @Test
    void testAuroraVeilFailsIfHailIsSuppressed() {
        battle.setWeather(Weather.HAIL, 5);
        battle.setWeatherSuppressed(true);
        final Move move = new AuroraVeil();

        move.execute(screenSetter, player.getActivePokemon(), battle);
        assertThat(player.getReflectTurns()).isZero();
        assertThat(player.getLightScreenTurns()).isZero();
        assertThat(player.getAuroraVeilTurns()).isZero();
    }

    @Test
    void testAuroraVeilFailsIfSnowIsSuppressed() {
        battle.setWeather(Weather.SNOW, 5);
        battle.setWeatherSuppressed(true);
        final Move move = new AuroraVeil();

        move.execute(screenSetter, player.getActivePokemon(), battle);
        assertThat(player.getReflectTurns()).isZero();
        assertThat(player.getLightScreenTurns()).isZero();
        assertThat(player.getAuroraVeilTurns()).isZero();
    }

    @Test
    void testAuroraVeilPersistsAfterWeatherEnds() {
        battle.setWeather(Weather.SNOW, 1);
        final Move move = new AuroraVeil();

        move.execute(screenSetter, player.getActivePokemon(), battle);
        assertThat(player.getReflectTurns()).isZero();
        assertThat(player.getLightScreenTurns()).isZero();
        assertThat(player.getAuroraVeilTurns()).isEqualTo(5);

        battle.decrementAllCounters();
        assertThat(battle.getWeather()).isEqualTo(Weather.NONE);
        assertThat(player.getAuroraVeilTurns()).isEqualTo(4);
    }

    @Test
    void testLightClayExtendsDurationFrom5To8() {
        battle = new DummyBattle(
            List.of(screenSetter, getDummyPokemon(), getDummyPokemon()),
            List.of(opponent, getDummyPokemon(), getDummyPokemon()), 3);
        player = battle.getPlayerOne();
        for (Pokemon mon : player.getTeam()) {
            mon.setHeldItem(LIGHT_CLAY);
        }
        battle.setWeather(Weather.SNOW, 1);

        new Reflect().execute(player.getActivePokemon().get(0), player.getActivePokemon(), battle);
        new LightScreen().execute(player.getActivePokemon().get(1), player.getActivePokemon(), battle);
        new AuroraVeil().execute(player.getActivePokemon().get(2), player.getActivePokemon(), battle);

        assertThat(player.getAuroraVeilTurns()).isEqualTo(8);
        assertThat(player.getReflectTurns()).isEqualTo(8);
        assertThat(player.getLightScreenTurns()).isEqualTo(8);
    }

    @Test
    void reflectHalvesPhysicalDamage() {

        player.setReflectTurns(1);
        final Move attack = new Tackle();

        attack.execute(opponent, List.of(screenSetter), battle);
        final int halvedDamage = screenSetter.getMaxHP() - screenSetter.getCurrentHP();
        screenSetter.maxPotion();

        player.setReflectTurns(0);
        attack.execute(opponent, List.of(screenSetter), battle);
        final int fullDamage = screenSetter.getMaxHP() - screenSetter.getCurrentHP();

        assertThat(fullDamage).isEqualTo(halvedDamage * 2);
    }

    @Test
    void lightScreenHalvesSpecialDamage() {

        player.setLightScreenTurns(1);
        final Move attack = new Surf();

        attack.execute(opponent, List.of(screenSetter), battle);
        final int halvedDamage = screenSetter.getMaxHP() - screenSetter.getCurrentHP();
        screenSetter.maxPotion();

        player.setLightScreenTurns(0);
        attack.execute(opponent, List.of(screenSetter), battle);
        final int fullDamage = screenSetter.getMaxHP() - screenSetter.getCurrentHP();

        assertThat(fullDamage).isEqualTo(halvedDamage * 2);
    }

    @Test
    void auroraVeilHalvesPhysicalDamage() {

        player.setAuroraVeilTurns(1);
        final Move attack = new Tackle();

        attack.execute(opponent, List.of(screenSetter), battle);
        final int halvedDamage = screenSetter.getMaxHP() - screenSetter.getCurrentHP();
        screenSetter.maxPotion();

        player.setAuroraVeilTurns(0);
        attack.execute(opponent, List.of(screenSetter), battle);
        final int fullDamage = screenSetter.getMaxHP() - screenSetter.getCurrentHP();

        assertThat(fullDamage).isEqualTo(halvedDamage * 2);
    }

    @Test
    void auroraVeilHalvesSpecialDamage() {

        player.setAuroraVeilTurns(1);
        final Move attack = new Surf();

        attack.execute(opponent, List.of(screenSetter), battle);
        final int halvedDamage = screenSetter.getMaxHP() - screenSetter.getCurrentHP();
        screenSetter.maxPotion();

        player.setAuroraVeilTurns(0);
        attack.execute(opponent, List.of(screenSetter), battle);
        final int fullDamage = screenSetter.getMaxHP() - screenSetter.getCurrentHP();

        assertThat(fullDamage).isEqualTo(halvedDamage * 2);
    }

    @Test
    void reflectAndAuroraVeilDoNotStack() {

        player.setAuroraVeilTurns(1);
        player.setReflectTurns(1);
        final Move attack = new Tackle();

        attack.execute(opponent, List.of(screenSetter), battle);
        final int damageWithBothScreens = screenSetter.getMaxHP() - screenSetter.getCurrentHP();
        screenSetter.maxPotion();

        player.setAuroraVeilTurns(0);
        attack.execute(opponent, List.of(screenSetter), battle);
        final int damageWithOneScreen = screenSetter.getMaxHP() - screenSetter.getCurrentHP();

        assertThat(damageWithOneScreen).isEqualTo(damageWithBothScreens);
    }

    @Test
    void lightScreenAndAuroraVeilDoNotStack() {

        player.setAuroraVeilTurns(1);
        player.setLightScreenTurns(1);
        final Move attack = new Surf();

        attack.execute(opponent, List.of(screenSetter), battle);
        final int damageWithBothScreens = screenSetter.getMaxHP() - screenSetter.getCurrentHP();
        screenSetter.maxPotion();

        player.setAuroraVeilTurns(0);
        attack.execute(opponent, List.of(screenSetter), battle);
        final int damageWithOneScreen = screenSetter.getMaxHP() - screenSetter.getCurrentHP();

        assertThat(damageWithOneScreen).isEqualTo(damageWithBothScreens);
    }

    @Test
    void criticalHitsBypassScreensForPhysicalAttacks() {

        player.setAuroraVeilTurns(5);
        player.setReflectTurns(5);

        final Move attack = new Tackle();
        attack.execute(opponent, List.of(screenSetter), battle);
        final int damageWithScreens = screenSetter.getMaxHP() - screenSetter.getCurrentHP();
        screenSetter.maxPotion();

        Main.CRIT_RANDOM = ZERO_RANDOM;
        attack.execute(opponent, List.of(screenSetter), battle);
        final double damageWithCrit = screenSetter.getMaxHP() - screenSetter.getCurrentHP();

        assertThat(damageWithCrit).isEqualTo(damageWithScreens * 2 * Move.REGULAR_CRIT_MULTIPLIER);
    }

    @Test
    void criticalHitsBypassScreensForSpecialAttacks() {

        player.setAuroraVeilTurns(5);
        player.setLightScreenTurns(5);

        final Move attack = new Surf();
        attack.execute(opponent, List.of(screenSetter), battle);
        final int damageWithScreens = screenSetter.getMaxHP() - screenSetter.getCurrentHP();
        screenSetter.maxPotion();

        Main.CRIT_RANDOM = ZERO_RANDOM;
        attack.execute(opponent, List.of(screenSetter), battle);
        final double damageWithCrit = screenSetter.getMaxHP() - screenSetter.getCurrentHP();

        assertThat(damageWithCrit).isEqualTo(damageWithScreens * 2 * Move.REGULAR_CRIT_MULTIPLIER);
    }

    @Test
    void twoThirdsPhysicalDamageWhenReflectIsUpInMultiBattle() {
        final Move physicalAttack = new Earthquake();
        final Pokemon mon1 = getDummyPokemon();
        final Pokemon mon2 = getDummyPokemon();
        final Pokemon mon3 = getDummyPokemon();
        battle = new DummyBattle(
            List.of(screenSetter, getDummyPokemon(), getDummyPokemon()),
            List.of(getDummyPokemon(), opponent, getDummyPokemon()), 3);

        player = battle.getPlayerOne();
        player.setReflectTurns(5);

        physicalAttack.execute(opponent, player.getActivePokemon(), battle);
        final int physicalDamageWithReflect = mon1.getMaxHP() - mon1.getCurrentHP();
        assertThat(physicalDamageWithReflect).isEqualTo(mon2.getMaxHP() - mon2.getCurrentHP())
            .isEqualTo(mon3.getMaxHP() - mon3.getCurrentHP());
        player.getActivePokemon().forEach(Pokemon::maxPotion);

        player.setReflectTurns(0);
        physicalAttack.execute(opponent, player.getActivePokemon(), battle);
        final int physicalDamageNoReflect = mon1.getMaxHP() - mon1.getCurrentHP();
        assertThat(physicalDamageNoReflect).isEqualTo(mon2.getMaxHP() - mon2.getCurrentHP())
            .isEqualTo(mon3.getMaxHP() - mon3.getCurrentHP());

        assertThat((double) physicalDamageWithReflect).isEqualTo(physicalDamageNoReflect * 2732 / 4096d);
    }

    @Test
    void twoThirdsSpecialDamageWhenLightScreenIsUpInMultiBattle() {
        final Move specialAttack = new Surf();
        final Pokemon mon1 = getDummyPokemon();
        final Pokemon mon2 = getDummyPokemon();
        final Pokemon mon3 = getDummyPokemon();
        battle = new DummyBattle(
            List.of(screenSetter, getDummyPokemon(), getDummyPokemon()),
            List.of(getDummyPokemon(), opponent, getDummyPokemon()), 3);

        player = battle.getPlayerOne();
        player.setLightScreenTurns(5);

        specialAttack.execute(opponent, player.getActivePokemon(), battle);
        final int specialDamageWithLightScreen = mon1.getMaxHP() - mon1.getCurrentHP();
        assertThat(specialDamageWithLightScreen).isEqualTo(mon2.getMaxHP() - mon2.getCurrentHP())
            .isEqualTo(mon3.getMaxHP() - mon3.getCurrentHP());
        player.getActivePokemon().forEach(Pokemon::maxPotion);

        player.setLightScreenTurns(0);
        specialAttack.execute(opponent, player.getActivePokemon(), battle);
        final int specialDamageNoScreen = mon1.getMaxHP() - mon1.getCurrentHP();
        assertThat(specialDamageNoScreen).isEqualTo(mon2.getMaxHP() - mon2.getCurrentHP())
            .isEqualTo(mon3.getMaxHP() - mon3.getCurrentHP());

        assertThat((double) specialDamageWithLightScreen).isEqualTo(specialDamageNoScreen * 2732 / 4096d);
    }

    @Test
    void twoThirdsPhysicalDamageWhenAuroraVeilIsUpInMultiBattle() {
        final Move physicalAttack = new Earthquake();
        final Pokemon mon1 = getDummyPokemon();
        final Pokemon mon2 = getDummyPokemon();
        final Pokemon mon3 = getDummyPokemon();
        battle = new DummyBattle(
            List.of(screenSetter, getDummyPokemon(), getDummyPokemon()),
            List.of(getDummyPokemon(), opponent, getDummyPokemon()), 3);

        player = battle.getPlayerOne();
        player.setAuroraVeilTurns(5);

        physicalAttack.execute(opponent, player.getActivePokemon(), battle);
        final int physicalDamageWithReflect = mon1.getMaxHP() - mon1.getCurrentHP();
        assertThat(physicalDamageWithReflect).isEqualTo(mon2.getMaxHP() - mon2.getCurrentHP())
            .isEqualTo(mon3.getMaxHP() - mon3.getCurrentHP());
        player.getActivePokemon().forEach(Pokemon::maxPotion);

        player.setAuroraVeilTurns(0);
        physicalAttack.execute(opponent, player.getActivePokemon(), battle);
        final int physicalDamageNoReflect = mon1.getMaxHP() - mon1.getCurrentHP();
        assertThat(physicalDamageNoReflect).isEqualTo(mon2.getMaxHP() - mon2.getCurrentHP())
            .isEqualTo(mon3.getMaxHP() - mon3.getCurrentHP());

        assertThat((double) physicalDamageWithReflect).isEqualTo(physicalDamageNoReflect * 2732 / 4096d);
    }

    @Test
    void twoThirdsSpecialDamageWhenAuroraVeilIsUpInMultiBattle() {
        final Move specialAttack = new Surf();
        final Pokemon mon1 = getDummyPokemon();
        final Pokemon mon2 = getDummyPokemon();
        final Pokemon mon3 = getDummyPokemon();
        battle = new DummyBattle(
            List.of(screenSetter, getDummyPokemon(), getDummyPokemon()),
            List.of(getDummyPokemon(), opponent, getDummyPokemon()), 3);

        player = battle.getPlayerOne();
        player.setAuroraVeilTurns(5);

        specialAttack.execute(opponent, player.getActivePokemon(), battle);
        final int specialDamageWithLightScreen = mon1.getMaxHP() - mon1.getCurrentHP();
        assertThat(specialDamageWithLightScreen).isEqualTo(mon2.getMaxHP() - mon2.getCurrentHP())
            .isEqualTo(mon3.getMaxHP() - mon3.getCurrentHP());
        player.getActivePokemon().forEach(Pokemon::maxPotion);

        player.setAuroraVeilTurns(0);
        specialAttack.execute(opponent, player.getActivePokemon(), battle);
        final int specialDamageNoScreen = mon1.getMaxHP() - mon1.getCurrentHP();
        assertThat(specialDamageNoScreen).isEqualTo(mon2.getMaxHP() - mon2.getCurrentHP())
            .isEqualTo(mon3.getMaxHP() - mon3.getCurrentHP());

        assertThat((double) specialDamageWithLightScreen).isEqualTo(specialDamageNoScreen * 2732 / 4096d);
    }
}
