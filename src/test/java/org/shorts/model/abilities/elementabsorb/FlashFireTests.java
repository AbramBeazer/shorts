package org.shorts.model.abilities.elementabsorb;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.Ember;
import org.shorts.model.moves.HeatCrash;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.WillOWisp;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

public class FlashFireTests {

    private Pokemon ffMon;
    private FlashFire ability;
    private Pokemon other;
    private Battle battle;
    private Move ember;

    @BeforeEach
    void setup() {
        ffMon = getDummyPokemon();
        ability = new FlashFire();
        ffMon.setAbility(ability);
        other = getDummyPokemon();
        battle = new DummyBattle(ffMon, other);
        ember = new Ember();
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
    }

    @Test
    void testNoDamageFromFireMove() {
        assertThat(ffMon.beforeHit(other, battle, ember)).isZero();
        ember.execute(other, List.of(ffMon), battle);
        assertThat(ffMon.getMaxHP()).isEqualTo(ffMon.getCurrentHP());
    }

    @Test
    void testFireBoostedAfterHit() {
        assertThat(ffMon.getAttackMultipliersFromAbilityAndItem(other, battle, ember)).isEqualTo(1);
        ember.execute(ffMon, List.of(other), battle);
        int baseDamage = other.getMaxHP() - other.getCurrentHP();
        other.setCurrentHP(other.getMaxHP());

        ember.execute(other, List.of(ffMon), battle);
        assertThat(ability.isActivated()).isTrue();

        assertThat(ffMon.getAttackMultipliersFromAbilityAndItem(
            other,
            battle,
            ember)).isEqualTo(FlashFire.MULTIPLIER);
        ember.execute(ffMon, List.of(other), battle);
        int boostedDamage = other.getMaxHP() - other.getCurrentHP();
        assertThat(boostedDamage).isGreaterThan(baseDamage);
    }

    @Test
    void testFireBoostedPersistsAfterMultipleAttacks() {
        assertThat(ffMon.getAttackMultipliersFromAbilityAndItem(other, battle, ember)).isEqualTo(1);
        ember.execute(ffMon, List.of(other), battle);
        int baseDamage = other.getMaxHP() - other.getCurrentHP();
        other.setCurrentHP(other.getMaxHP());

        ember.execute(other, List.of(ffMon), battle);
        assertThat(ability.isActivated()).isTrue();

        assertThat(ffMon.getAttackMultipliersFromAbilityAndItem(
            other,
            battle,
            ember)).isEqualTo(FlashFire.MULTIPLIER);
        ember.execute(ffMon, List.of(other), battle);
        int boostedDamage = other.getMaxHP() - other.getCurrentHP();
        assertThat(boostedDamage).isGreaterThan(baseDamage);

        other.setCurrentHP(other.getMaxHP());

        assertThat(ffMon.getAttackMultipliersFromAbilityAndItem(
            other,
            battle,
            ember)).isEqualTo(FlashFire.MULTIPLIER);
        ember.execute(ffMon, List.of(other), battle);
        int boostedDamage2 = other.getMaxHP() - other.getCurrentHP();
        assertThat(boostedDamage2).isEqualTo(boostedDamage).isGreaterThan(baseDamage);
    }

    @Test
    void testDamagingMoveThawsAndActivatesEvenIfTargetIsFrozen() {
        final Move move = new HeatCrash();
        assertThat(ffMon.getAttackMultipliersFromAbilityAndItem(other, battle, move)).isEqualTo(1);

        move.execute(ffMon, List.of(other), battle);
        int baseDamage = other.getMaxHP() - other.getCurrentHP();
        other.setCurrentHP(other.getMaxHP());

        assertThat(ffMon.beforeHit(other, battle, move)).isZero();
        ffMon.setStatus(Status.FREEZE);
        move.execute(other, List.of(ffMon), battle);
        assertThat(ffMon.getMaxHP()).isEqualTo(ffMon.getCurrentHP());
        assertThat(ability.isActivated()).isTrue();
        assertThat(ffMon.getStatus()).isEqualTo(Status.NONE);

        assertThat(ffMon.getAttackMultipliersFromAbilityAndItem(
            other,
            battle,
            move)).isEqualTo(FlashFire.MULTIPLIER);
        move.execute(ffMon, List.of(other), battle);
        int boostedDamage = other.getMaxHP() - other.getCurrentHP();
        assertThat(boostedDamage).isGreaterThan(baseDamage);
    }

    @Test
    void testFireStatusMoveActivatesOnFrozenTargetButDoesNotThaw() {
        final WillOWisp wisp = new WillOWisp();

        assertThat(ffMon.getAttackMultipliersFromAbilityAndItem(other, battle, ember)).isEqualTo(1);

        ember.execute(ffMon, List.of(other), battle);
        int baseDamage = other.getMaxHP() - other.getCurrentHP();
        other.setCurrentHP(other.getMaxHP());

        assertThat(ffMon.beforeHit(other, battle, ember)).isZero();
        ffMon.setStatus(Status.FREEZE);
        wisp.execute(other, List.of(ffMon), battle);
        assertThat(ffMon.getMaxHP()).isEqualTo(ffMon.getCurrentHP());
        assertThat(ability.isActivated()).isTrue();
        assertThat(ffMon.getStatus()).isEqualTo(Status.FREEZE);

        assertThat(ffMon.getAttackMultipliersFromAbilityAndItem(
            other,
            battle,
            ember)).isEqualTo(FlashFire.MULTIPLIER);
        ember.execute(ffMon, List.of(other), battle);
        int boostedDamage = other.getMaxHP() - other.getCurrentHP();
        assertThat(boostedDamage).isGreaterThan(baseDamage);
    }

    @Test
    void testDeactivatesOnSwitch() {
        assertThat(false).isTrue();
    }

    @Test
    void testBoostNotPassedByBatonPass() {
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotActivateWhenProtected() {
        ffMon.addVolatileStatus(new VolatileStatus(VolatileStatusType.PROTECTED, 1));
        ember.execute(other, List.of(ffMon), battle);
        assertThat(ability.isActivated()).isFalse();
    }

    @Test
    void testActivatesFromWillOWispButIsNotBurned() {
        final Move willOWisp = new WillOWisp();

        assertThat(ffMon.getAttackMultipliersFromAbilityAndItem(other, battle, ember)).isEqualTo(1);

        ember.execute(ffMon, List.of(other), battle);
        int baseDamage = other.getMaxHP() - other.getCurrentHP();
        other.setCurrentHP(other.getMaxHP());

        assertThat(ffMon.beforeHit(other, battle, willOWisp)).isZero();
        willOWisp.execute(other, List.of(ffMon), battle);
        assertThat(ffMon.getMaxHP()).isEqualTo(ffMon.getCurrentHP());
        assertThat(ability.isActivated()).isTrue();

        assertThat(ffMon.getStatus()).isEqualTo(Status.NONE);

        assertThat(ffMon.getAttackMultipliersFromAbilityAndItem(
            other,
            battle,
            ember)).isEqualTo(FlashFire.MULTIPLIER);
        ember.execute(ffMon, List.of(other), battle);
        int boostedDamage = other.getMaxHP() - other.getCurrentHP();
        assertThat(boostedDamage).isGreaterThan(baseDamage);
    }

    @Test
    void testMultipleHitsDontStack() {
        assertThat(false).isTrue();
    }
}
