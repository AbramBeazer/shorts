package org.shorts.model.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.battle.Terrain;
import org.shorts.battle.Weather;
import org.shorts.model.abilities.Protosynthesis;
import org.shorts.model.abilities.QuarkDrive;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.items.BoosterEnergy.BOOSTER_ENERGY;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class BoosterEnergyTests {

    private Pokemon user;
    private Pokemon opponent;
    private Battle battle;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        user.setHeldItem(BOOSTER_ENERGY);
        opponent = getDummyPokemon();
        battle = new DummySingleBattle(user, opponent);
    }

    @Test
    void testIsNotConsumedIfHolderHasProtosynthesisAndSunIsOut() {
        user.setAbility(new Protosynthesis());
        battle.setWeather(Weather.SUN, -1);

        assertThat(((Protosynthesis) user.getAbility()).isActivatedBySun()).isTrue();
        assertThat(((Protosynthesis) user.getAbility()).getBoostedStat()).isNotNull();
        assertThat(user.getHeldItem()).isEqualTo(BOOSTER_ENERGY);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);
    }

    @Test
    void testIsNotConsumedIfHolderHasProtosynthesisAndExtremeSunIsOut() {
        user.setAbility(new Protosynthesis());
        battle.setWeather(Weather.EXTREME_SUN, -1);

        assertThat(((Protosynthesis) user.getAbility()).isActivatedBySun()).isTrue();
        assertThat(((Protosynthesis) user.getAbility()).getBoostedStat()).isNotNull();
        assertThat(user.getHeldItem()).isEqualTo(BOOSTER_ENERGY);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);
    }

    @Test
    void testShouldBeConsumedWhenProtosynthesisEndsDueToWeatherChange() {
        user.setAbility(new Protosynthesis());
        battle.setWeather(Weather.SUN, -1);

        assertThat(((Protosynthesis) user.getAbility()).getBoostedStat()).isNotNull();
        assertThat(((Protosynthesis) user.getAbility()).isActivatedBySun()).isTrue();
        assertThat(user.getHeldItem()).isEqualTo(BOOSTER_ENERGY);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);

        battle.setWeather(Weather.RAIN, -1);
        assertThat(((Protosynthesis) user.getAbility()).isActivatedBySun()).isFalse();
        assertThat(((Protosynthesis) user.getAbility()).getBoostedStat()).isNotNull();
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(BOOSTER_ENERGY);
    }

    @Test
    void testShouldBeConsumedWhenProtosynthesisEndsDueToWeatherSuppression() {
        user.setAbility(new Protosynthesis());
        battle.setWeather(Weather.SUN, -1);

        assertThat(((Protosynthesis) user.getAbility()).getBoostedStat()).isNotNull();
        assertThat(((Protosynthesis) user.getAbility()).isActivatedBySun()).isTrue();
        assertThat(user.getHeldItem()).isEqualTo(BOOSTER_ENERGY);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);

        battle.setWeatherSuppressed(true);
        assertThat(((Protosynthesis) user.getAbility()).isActivatedBySun()).isFalse();
        assertThat(((Protosynthesis) user.getAbility()).getBoostedStat()).isNotNull();
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(BOOSTER_ENERGY);
    }

    @Test
    void testActivatesIfEntersWhenWeatherIsSuppressed() {
        battle.setWeather(Weather.SUN, -1);
        battle.setWeatherSuppressed(true);
        user.setAbility(new Protosynthesis());
        user.afterEntry(battle);

        assertThat(((Protosynthesis) user.getAbility()).isActivatedBySun()).isFalse();
        assertThat(((Protosynthesis) user.getAbility()).getBoostedStat()).isNotNull();
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(BOOSTER_ENERGY);
    }

    @Test
    void testActivatesInNoWeatherOrTerrain() {
        user.setAbility(new Protosynthesis());
        user.afterEntry(battle);

        assertThat(((Protosynthesis) user.getAbility()).isActivatedBySun()).isFalse();
        assertThat(((Protosynthesis) user.getAbility()).getBoostedStat()).isNotNull();
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(BOOSTER_ENERGY);
    }

    @Test
    void testIsNotConsumedIfHolderHasQuarkDriveAndElectricTerrainApplies() {
        user.setAbility(new QuarkDrive());
        battle.setTerrain(Terrain.ELECTRIC, -1);

        assertThat(((QuarkDrive) user.getAbility()).isActivatedByTerrain()).isTrue();
        assertThat(((QuarkDrive) user.getAbility()).getBoostedStat()).isNotNull();
        assertThat(user.getHeldItem()).isEqualTo(BOOSTER_ENERGY);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);
    }

    @Test
    void testIsConsumedIfQuarkDriveEndsDueToTerrainLoss() {
        user.setAbility(new QuarkDrive());
        battle.setTerrain(Terrain.ELECTRIC, -1);

        assertThat(((QuarkDrive) user.getAbility()).isActivatedByTerrain()).isTrue();
        assertThat(((QuarkDrive) user.getAbility()).getBoostedStat()).isNotNull();
        assertThat(user.getHeldItem()).isEqualTo(BOOSTER_ENERGY);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);

        battle.setTerrain(Terrain.NONE, -1);
        assertThat(((QuarkDrive) user.getAbility()).isActivatedByTerrain()).isFalse();
        assertThat(((QuarkDrive) user.getAbility()).getBoostedStat()).isNotNull();
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(BOOSTER_ENERGY);
    }

}
