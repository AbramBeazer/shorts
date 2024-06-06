package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.battle.Weather;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.items.BoosterEnergy.BOOSTER_ENERGY;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class ProtosynthesisTests {

    private Pokemon user;
    private Protosynthesis protosynthesis;
    private Pokemon opponent;
    private Battle battle;

    @BeforeEach
    void setup() {
        protosynthesis = new Protosynthesis();
        user = getDummyPokemon();
        user.setAbility(protosynthesis);
        opponent = getDummyPokemon();
        battle = new DummySingleBattle(user, opponent);
    }

    @Test
    void testActivatesInSun() {
        battle.setWeather(Weather.SUN, -1);

        assertThat(protosynthesis.isActivatedBySun()).isTrue();
        assertThat(protosynthesis.getBoostedStat()).isNotNull();
    }

    @Test
    void testActivatesInExtremeSun() {
        battle.setWeather(Weather.EXTREME_SUN, -1);

        assertThat(protosynthesis.isActivatedBySun()).isTrue();
        assertThat(protosynthesis.getBoostedStat()).isNotNull();
    }

    @Test
    void testDoesNotActivateWithoutBoosterEnergyInSuppressedWeather() {
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);

        battle.setWeatherSuppressed(true);
        battle.setWeather(Weather.SUN, -1);

        assertThat(protosynthesis.isActivatedBySun()).isFalse();
        assertThat(protosynthesis.getBoostedStat()).isNull();
    }

    @Test
    void testDoesNotActivateWithoutBoosterEnergyInNoWeather() {
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(battle.getWeather()).isEqualTo(Weather.NONE);

        protosynthesis.checkActivation(user, battle);

        assertThat(protosynthesis.isActivatedBySun()).isFalse();
        assertThat(protosynthesis.getBoostedStat()).isNull();
    }

    @Test
    void testIgnoresNeutralizingGas() {

        assertThat(false).isTrue();

    }

    @Test
    void testGastroAcidFails() {
        assertThat(false).isTrue();
    }

    @Test
    void testRolePlayFails() {
        assertThat(false).isTrue();
    }

    @Test
    void testSkillSwapFails() {
        assertThat(false).isTrue();
    }

    @Test
    void testDoodleFails() {
        assertThat(false).isTrue();
    }

    @Test
    void testSimpleBeamChangesAbility() {
        assertThat(false).isTrue();
    }

    @Test
    void testWorrySeedChangesAbility() {
        assertThat(false).isTrue();
    }

    @Test
    void testReceiverCannotCopyProtosynthesis() {
        assertThat(false).isTrue();
    }

    @Test
    void testTraceCannotCopyProtosynthesis() {
        assertThat(false).isTrue();
    }

    @Test
    void testPowerOfAlchemyCannotCopyProtosynthesis() {
        assertThat(false).isTrue();
    }

    @Test
    void testLingeringAromaCannotReplaceProtosynthesis() {
        assertThat(false).isTrue();
    }

    @Test
    void testMummyCannotReplaceProtosynthesis() {
        assertThat(false).isTrue(); //TODO: Verify what should happen here.
    }

    @Test
    void testWanderingSpiritCannotSwitchWithProtosynthesis() {
        assertThat(false).isTrue(); //TODO: Verify what should happen here.
    }

    @Test
    void testOpponentThatCopiesAbilityWithImposterDoesNotActivateProtosynthesis() {
        //        opponent.setAbility(IMPOSTER);
        opponent.setHeldItem(BOOSTER_ENERGY);
        battle.setWeather(Weather.SUN, -1);

        //TODO: Implement
        assertThat(false).isTrue();
    }

    @Test
    void testOpponentThatCopiesAbilityWithTransformDoesNotActivateProtosynthesis() {
        //        opponent.setMoves(List.of(new TransformMove()));
        opponent.setHeldItem(BOOSTER_ENERGY);
        battle.setWeather(Weather.SUN, -1);
        //TODO: Implement

        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotActivateForHP() {
        assertThat(user.getCurrentHP()).isGreaterThan((int) user.getStatApplyStage(StatEnum.ATK));
        protosynthesis.grantStatBoost(user);
        assertThat(protosynthesis.getBoostedStat()).isNotEqualTo(StatEnum.HP);
    }

    @Test
    void testBoostsSpeedByFiftyPercent() {
        user.setSpeed(600);
        protosynthesis.grantStatBoost(user);

        assertThat(protosynthesis.getBoostedStat()).isEqualTo(StatEnum.SPEED);
        assertThat(protosynthesis.onCalculateAttack(user)).isEqualTo(Protosynthesis.SPEED_MULTIPLIER);
        assertThat(user.calculateSpeed()).isEqualTo(
            user.getStatApplyStage(StatEnum.SPEED) * Protosynthesis.SPEED_MULTIPLIER);
    }

    @Test
    void testBoostsAttackingAndDefendingStatsByThirtyPercent() {
        user.setDefense(1000);
        protosynthesis.grantStatBoost(user);

        assertThat(protosynthesis.getBoostedStat()).isEqualTo(StatEnum.DEF);
        assertThat(protosynthesis.onCalculateDefense(user)).isEqualTo(Protosynthesis.MULTIPLIER);
        assertThat(user.calculateDefense()).isEqualTo(
            user.getStatApplyStage(StatEnum.DEF) * Protosynthesis.MULTIPLIER);
    }

    @Test
    void testFactorsInStatChanges() {
        user.setDefense(300);
        user.setStageSpeed(6);
        protosynthesis.grantStatBoost(user);

        assertThat(protosynthesis.getBoostedStat()).isEqualTo(StatEnum.SPEED);
        assertThat(protosynthesis.onCalculateAttack(user)).isEqualTo(Protosynthesis.SPEED_MULTIPLIER);
        assertThat(user.calculateSpeed()).isEqualTo(
            user.getStatApplyStage(StatEnum.SPEED) * Protosynthesis.SPEED_MULTIPLIER);
    }

    @Test
    void testActivatesEvenWhenHoldingUtilityUmbrella() {
        assertThat(false).isTrue();
    }

    @Test
    void testActivatesWithBoosterEnergyWithoutSun() {
        assertThat(false).isTrue();
    }

    @Test
    void testStatIncreaseIsNotBatonPassable() {
        assertThat(false).isTrue();
    }

    @Test
    void testIgnoresStatBoostFromHeldItems() {
        assertThat(false).isTrue();
    }

    @Test
    void testStatRaiseEndsWhenSunDisappearsIfActivatedBySun() {
        assertThat(false).isTrue();
    }

    @Test
    void testStatRaisePersistsWhenSunDisappearsIfActivatedByBoosterEnergy() {
        assertThat(false).isTrue();
    }

    @Test
    void testStatTiebreakers() {
        user.setAttack(500);
        user.setDefense(500);
        protosynthesis.grantStatBoost(user);

        assertThat(user.calculateAttack()).isGreaterThan(user.calculateDefense());
    }
}
