package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.battle.Weather;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.items.BoosterEnergy.BOOSTER_ENERGY;
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
        battle = new DummySingleBattle();
    }

    @Test
    void testActivatesInSun() {
        assertThat(false).isTrue();
    }

    @Test
    void testActivatesInExtremeSun() {
        assertThat(false).isTrue();

    }

    @Test
    void testDoesNotActivateWithoutBoosterEnergyInSuppressedWeather() {
        assertThat(false).isTrue();

    }

    @Test
    void testDoesNotActivateWithoutBoosterEnergyInNoWeather() {
        assertThat(false).isTrue();

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

        assertThat(false).isTrue();
    }

    @Test
    void testOpponentThatCopiesAbilityWithTransformDoesNotActivateProtosynthesis() {
        //        opponent.setMoves(List.of(new TransformMove()));
        opponent.setHeldItem(BOOSTER_ENERGY);
        battle.setWeather(Weather.SUN, -1);

        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotActivateForHP() {

        assertThat(false).isTrue();
    }

    @Test
    void testBoostsSpeedByFiftyPercent() {
        assertThat(false).isTrue();
    }

    @Test
    void testBoostsAttackingAndDefendingStatsByThirtyPercent() {
        assertThat(false).isTrue();
    }

    @Test
    void testFactorsInStatChanges() {
        assertThat(false).isTrue();
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
