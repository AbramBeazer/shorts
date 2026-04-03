package org.shorts.model.abilities;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.model.moves.Curse;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Tackle;
import org.shorts.model.moves.ThunderPunch;
import org.shorts.model.moves.Toxic;
import org.shorts.model.moves.WaterPulse;
import org.shorts.model.moves.recoil.Struggle;
import org.shorts.model.moves.switchtarget.Roar;
import org.shorts.model.moves.switchtarget.Whirlwind;
import org.shorts.model.moves.thawing.FlameWheel;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomMiss.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;
import static org.shorts.model.types.Type.*;

class ProteanLiberoTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private ProteanLibero protean;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        protean = ProteanLibero.createProtean();
        user.setAbility(protean);
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testUserChangesTypeBeforeMoveExecutesAndGetsSTAB() {
        final Move move = new FlameWheel();
        final Set<Type> expectedType = Set.of(move.getType());

        user.addVolatileStatus(VolatileStatus.ABILITY_SUPPRESSED);
        new Turn(user, move, 0).takeTurn(battle);
        assertThat(user.getTypes()).isNotEqualTo(expectedType);
        final double baseDamage = target.getMaxHP() - target.getCurrentHP();
        assertThat(baseDamage).isPositive();

        target.fullRestore();

        user.removeVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED);
        new Turn(user, move, 0).takeTurn(battle);
        assertThat(user.getTypes()).isEqualTo(expectedType);
        final double stabDamage = target.getMaxHP() - target.getCurrentHP();
        assertThat(stabDamage).isEqualTo(baseDamage * Type.STAB);
    }

    @Test
    void testDoesNotActivateIfUsersLoneTypeIsMoveType() {
        final Move move = new FlameWheel();
        final Set<Type> expectedType = Set.of(move.getType());
        user.setTypes(expectedType);

        new Turn(user, move, 0).takeTurn(battle);
        assertThat(user.getTypes()).isEqualTo(expectedType);
        assertThat(protean.isActivated()).isFalse();
    }

    @Test
    void testUserChangesTypeAndHitsWithToxic() {
        user.setStageAccuracy(-6);
        target.setStageEvasion(6);
        Main.HIT_RANDOM = ALWAYS_MISS;
        new Turn(user, new Toxic(), 0).takeTurn(battle);
        assertThat(user.getTypes()).isEqualTo(Set.of(POISON));
        assertThat(target.getStatus().getType()).isEqualTo(StatusType.TOXIC_POISON);
    }

    @Test
    void testDoesNotActivateForStruggle() {
        final Set<Type> originalType = Set.of(FIRE);
        user.setTypes(originalType);
        new Turn(user, Struggle.STRUGGLE, 0).takeTurn(battle);
        assertThat(user.getTypes()).isEqualTo(originalType);
    }

    @Test
    void testOnlyActivatesOnce() {
        final Set<Type> expectedType = Set.of(FIRE);
        new Turn(user, new FlameWheel(), 0).takeTurn(battle);
        assertThat(user.getTypes()).isEqualTo(expectedType);
        new Turn(user, new WaterPulse(), 0).takeTurn(battle);
        assertThat(user.getTypes()).isEqualTo(expectedType);
        new Turn(user, new Tackle(), 0).takeTurn(battle);
        assertThat(user.getTypes()).isEqualTo(expectedType);
    }

    @Test
    void testDoesNotActivateIfImmobilizedByLove() {
        final Set<Type> expectedType = Set.of(FIRE);
        user.addVolatileStatus(VolatileStatus.INFATUATED);
        new Turn(user, new FlameWheel(), 0).takeTurn(battle);
        assertThat(user.getTypes()).isNotEqualTo(expectedType);
    }

    @Test
    void testDoesNotActivateIfHurtsSelfInConfusion() {
        final Set<Type> expectedType = Set.of(FIRE);
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.CONFUSED, 5));
        new Turn(user, new FlameWheel(), 0).takeTurn(battle);
        assertThat(user.getTypes()).isNotEqualTo(expectedType);
    }

    @Test
    void testDoesNotActivateIfParalyzed() {
        final Set<Type> expectedType = Set.of(FIRE);
        user.setStatus(Status.PARALYZE);
        new Turn(user, new FlameWheel(), 0).takeTurn(battle);
        assertThat(user.getTypes()).isNotEqualTo(expectedType);
    }

    @Test
    void testDoesNotActivateIfAsleep() {
        final Set<Type> expectedType = Set.of(FIRE);
        user.setStatus(Status.createSleep());
        new Turn(user, new FlameWheel(), 0).takeTurn(battle);
        assertThat(user.getTypes()).isNotEqualTo(expectedType);
    }

    @Test
    void testDoesNotActivateIfFrozen() {
        final Set<Type> expectedType = Set.of(WATER);
        user.setStatus(Status.FREEZE);
        new Turn(user, new WaterPulse(), 0).takeTurn(battle);
        assertThat(user.getTypes()).isNotEqualTo(expectedType);
    }

    @Test
    void testActivatesIfThawsSelf() {
        final Set<Type> expectedType = Set.of(FIRE);
        user.setStatus(Status.FREEZE);
        new Turn(user, new FlameWheel(), 0).takeTurn(battle);
        assertThat(user.getTypes()).isEqualTo(expectedType);
    }

    @Test
    void testNaturePower() {
        assertThat(false).isTrue();

    }

    @Test
    void testMetronome() {
        assertThat(false).isTrue();

    }

    @Test
    void testMoveThatChangesMoveType() {
        assertThat(false).isTrue();

    }

    @Test
    void testWithRagingBull() {
        assertThat(false).isTrue();
    }

    @Test
    void testWithElectrifyMakesProteanChangeUserToElectric() {//Electrify is not in Gen 9
        assertThat(false).isTrue();

    }

    @Test
    void testWithIonDeluge() { //Ion Deluge is not in Gen 8 or 9
        assertThat(false).isTrue();

    }

    @Test
    void testDoesNotActivateIfTerastallized() {
        user.setTeraType(ELECTRIC);
        user.terastallize();
        final Move move = new FlameWheel();
        new Turn(user, move).takeTurn(battle);

        assertThat(protean.isActivated()).isFalse();
        assertThat(user.getTypes()).doesNotContain(move.getType());
    }

    @Test
    void testTeraNeutralizesExistingEffectsOfProtean() {
        final Set<Type> originalTypes = user.getTypes();
        new Turn(user, new FlameWheel()).takeTurn(battle);

        user.setTeraType(ELECTRIC);
        user.terastallize();

        assertThat(user.getTypes()).isEqualTo(originalTypes);
        assertThat(user.hasVolatileStatus(VolatileStatusType.TYPE_CHANGE)).isFalse();
    }

    @Test
    void testActivatesAgainAfterSwitchingOutAndBackIn() {
        assertThat(false).isTrue();
    }

    @Test
    void testGhostCurseEvenIfCraftyShieldFollowMeRagePowder() {
        assertThat(false).isTrue();

    }

    @Test
    void testCurseWithElectrifyChangesStats() { //Electrify is not in Gen 9
        //        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.ELECTRIFIED));

        new Turn(user, new Curse()).takeTurn(battle);
        assertThat(user.getTypes()).isEqualTo(Set.of(ELECTRIC));
        assertThat(user.getStageAttack()).isOne();
        assertThat(user.getStageDefense()).isOne();
        assertThat(user.getStageSpeed()).isEqualTo(-1);
    }

    @Test
    void testWithCamoflauge() { // Camoflauge is not in Gen 8 or 9
        assertThat(false).isTrue();

    }

    @Test
    void testActivatesIfRoarFails() {
        target.setAbility(SuctionCups.SUCTION_CUPS);
        new Turn(user, new Roar(), 0).takeTurn(battle);
        assertThat(battle.getOpposingTrainer(user).getActivePokemon()).contains(target);
        assertThat(protean.isActivated()).isTrue();
    }

    @Test
    void testActivatesIfWhirlwindFails() {
        target.setAbility(SuctionCups.SUCTION_CUPS);
        new Turn(user, new Whirlwind(), 0).takeTurn(battle);
        assertThat(battle.getOpposingTrainer(user).getActivePokemon()).contains(target);
        assertThat(protean.isActivated()).isTrue();
    }

    @Test
    void testActivatesIfForestsCurseFails() {
        assertThat(false).isTrue();
    }

    @Test
    void testActivatesIfTrickOrTreatFails() {
        assertThat(false).isTrue();
    }

    @Test
    void testNewTypeMaintainedIfAbilityReplaced() {
        assertThat(false).isTrue();

    }

    @Test
    void testNewTypeMaintainedIfAbilityNullified() {
        assertThat(false).isTrue();
    }

    @Test
    void testActivatesIfTargetIsProtected() {
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.PROTECTED, 1));
        final Set<Type> expectedType = Set.of(ELECTRIC);
        new Turn(user, new ThunderPunch(), 0).takeTurn(battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.getTypes()).isEqualTo(expectedType);
    }

    @Test
    void testActivatesIfMoveMisses() {
        Main.HIT_RANDOM = ALWAYS_MISS;
        final Set<Type> expectedType = Set.of(ELECTRIC);
        new Turn(user, new ThunderPunch(), 0).takeTurn(battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.getTypes()).isEqualTo(expectedType);
    }

    @Test
    void testActivatesIfTargetIsSemiInvulnerable() {
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.SEMI_INVULNERABLE, 1));
        final Set<Type> expectedType = Set.of(ELECTRIC);
        new Turn(user, new ThunderPunch(), 0).takeTurn(battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.getTypes()).isEqualTo(expectedType);
    }

    @Test
    void testActivatesIfTargetIsImmune() {
        target.setTypes(Set.of(GROUND));
        final Set<Type> expectedType = Set.of(ELECTRIC);
        new Turn(user, new ThunderPunch(), 0).takeTurn(battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.getTypes()).isEqualTo(expectedType);
    }

}
