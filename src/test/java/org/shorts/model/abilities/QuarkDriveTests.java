package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Terrain;
import org.shorts.battle.Weather;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.items.BoosterEnergy.BOOSTER_ENERGY;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class QuarkDriveTests {

    private Pokemon user;
    private QuarkDrive quarkDrive;
    private Pokemon opponent;
    private Battle battle;

    @BeforeEach
    void setup() {
        quarkDrive = new QuarkDrive();
        user = getDummyPokemon();
        user.setAbility(quarkDrive);
        opponent = getDummyPokemon();
        battle = new DummyBattle(user, opponent);
    }

    @Test
    void testActivatesInElectricTerrain() {
        battle.setTerrain(Terrain.ELECTRIC, -1);

        assertThat(quarkDrive.isActivatedByTerrain()).isTrue();
        assertThat(quarkDrive.getBoostedStat()).isNotNull();
    }

    @Test
    void testDoesNotActivateWithoutBoosterEnergyWithoutElectricTerrain() {
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);

        battle.setTerrain(Terrain.MISTY, -1);

        assertThat(quarkDrive.isActivatedByTerrain()).isFalse();
        assertThat(quarkDrive.getBoostedStat()).isNull();
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
    void testReceiverCannotCopyQuarkDrive() {
        assertThat(false).isTrue();
    }

    @Test
    void testTraceCannotCopyQuarkDrive() {
        assertThat(false).isTrue();
    }

    @Test
    void testPowerOfAlchemyCannotCopyQuarkDrive() {
        assertThat(false).isTrue();
    }

    @Test
    void testLingeringAromaCannotReplaceQuarkDrive() {
        assertThat(false).isTrue();
    }

    @Test
    void testMummyCannotReplaceQuarkDrive() {
        assertThat(false).isTrue(); //TODO: Verify what should happen here.
    }

    @Test
    void testWanderingSpiritCannotSwitchWithQuarkDrive() {
        assertThat(false).isTrue(); //TODO: Verify what should happen here.
    }

    @Test
    void testOpponentThatCopiesAbilityWithImposterDoesNotActivateQuarkDrive() {
        //        opponent.setAbility(IMPOSTER);
        opponent.setHeldItem(BOOSTER_ENERGY);
        battle.setWeather(Weather.SUN, -1);
        //TODO: Implement

        assertThat(false).isTrue();
    }

    @Test
    void testOpponentThatCopiesAbilityWithTransformDoesNotActivateQuarkDrive() {
        //        opponent.setMoves(List.of(new TransformMove()));
        opponent.setHeldItem(BOOSTER_ENERGY);
        battle.setWeather(Weather.SUN, -1);
        //TODO: Implement
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotActivateForHP() {
        assertThat(user.getCurrentHP()).isGreaterThan((int) user.getStatApplyStage(StatEnum.ATK));
        quarkDrive.grantStatBoost(user);
        assertThat(quarkDrive.getBoostedStat()).isNotEqualTo(StatEnum.HP);
    }

    @Test
    void testBoostsSpeedByFiftyPercent() {
        user.setSpeed(600);
        quarkDrive.grantStatBoost(user);

        assertThat(quarkDrive.getBoostedStat()).isEqualTo(StatEnum.SPEED);
        assertThat(quarkDrive.onCalculateAttack(user)).isEqualTo(QuarkDrive.SPEED_MULTIPLIER);
        assertThat(user.calculateSpeed(battle)).isEqualTo(
            user.getStatApplyStage(StatEnum.SPEED) * QuarkDrive.SPEED_MULTIPLIER);
    }

    @Test
    void testBoostsAttackingAndDefendingStatsByThirtyPercent() {
        user.setDefense(1000);
        quarkDrive.grantStatBoost(user);

        assertThat(quarkDrive.getBoostedStat()).isEqualTo(StatEnum.DEF);
        assertThat(quarkDrive.onCalculateDefense(user)).isEqualTo(QuarkDrive.MULTIPLIER);
        assertThat(user.calculateDefense(battle)).isEqualTo(
            user.getStatApplyStage(StatEnum.DEF) * QuarkDrive.MULTIPLIER);
    }

    @Test
    void testFactorsInStatChanges() {
        user.setDefense(300);
        user.setStageSpeed(6);
        quarkDrive.grantStatBoost(user);

        assertThat(quarkDrive.getBoostedStat()).isEqualTo(StatEnum.SPEED);
        assertThat(quarkDrive.onCalculateAttack(user)).isEqualTo(QuarkDrive.SPEED_MULTIPLIER);
        assertThat(user.calculateSpeed(battle)).isEqualTo(
            user.getStatApplyStage(StatEnum.SPEED) * QuarkDrive.SPEED_MULTIPLIER);
    }

    @Test
    void testActivatesWithBoosterEnergyWithoutTerrain() {
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
    void testStatRaiseEndsWhenTerrainDisappearsIfActivatedByTerrain() {
        assertThat(false).isTrue();
    }

    @Test
    void testStatRaisePersistsWhenTerrainDisappearsIfActivatedByBoosterEnergy() {
        assertThat(false).isTrue();
    }

    @Test
    void testStatTiebreakers() {
        user.setAttack(500);
        user.setDefense(500);
        quarkDrive.grantStatBoost(user);

        assertThat(user.calculateAttack()).isGreaterThan(user.calculateDefense(battle));
    }
}
