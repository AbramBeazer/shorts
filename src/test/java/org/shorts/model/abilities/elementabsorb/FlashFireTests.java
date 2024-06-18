package org.shorts.model.abilities.elementabsorb;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.MockRandomReturnZero;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.moves.Ember;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.WillOWisp;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.assertThat;
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
        battle = new DummySingleBattle();
        ember = new Ember();
        Main.RANDOM = new MockRandomReturnZero();
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
    void testActivatesEvenIfUserIsFrozen() {
        ffMon.setStatus(Status.FREEZE);

        assertThat(ffMon.getAttackMultipliersFromAbilityAndItem(other, battle, ember)).isEqualTo(1);

        ember.execute(ffMon, List.of(other), battle);
        int baseDamage = other.getMaxHP() - other.getCurrentHP();
        other.setCurrentHP(other.getMaxHP());

        assertThat(ffMon.beforeHit(other, battle, ember)).isZero();
        ember.execute(other, List.of(ffMon), battle);
        assertThat(ffMon.getMaxHP()).isEqualTo(ffMon.getCurrentHP());
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