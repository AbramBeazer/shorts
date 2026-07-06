package org.shorts.model.moves;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.model.Sex;
import org.shorts.model.abilities.AttackDoublingAbility;
import org.shorts.model.abilities.Rivalry;
import org.shorts.model.abilities.Sturdy;
import org.shorts.model.abilities.Technician;
import org.shorts.model.items.ChoiceBand;
import org.shorts.model.items.FocusSash;
import org.shorts.model.items.LifeOrb;
import org.shorts.model.items.LightBall;
import org.shorts.model.items.NoItem;
import org.shorts.model.items.ThickClub;
import org.shorts.model.items.TypeBoostItem;
import org.shorts.model.pokemon.PokedexEntry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.HelpingHandStatus;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class HurtItselfInConfusionTests {

    private Pokemon user;
    private Battle battle;
    private final HurtItselfInConfusion move = HurtItselfInConfusion.HURT_ITSELF_IN_CONFUSION;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        battle = new DummyBattle(user, getDummyPokemon());
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testChoiceBandDoesNotBoostAttack() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        user.setHeldItem(ChoiceBand.CHOICE_BAND);
        final int boostedDamage = move.calculateDamage(user, user, battle);

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testThickClubDoesNotBoostAttack() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        user.setPokedexEntry(PokedexEntry.PokedexEntryBuilder.createNewInstance().setPokedexNo(104).build());
        user.setHeldItem(ThickClub.THICK_CLUB);
        final int boostedDamage = move.calculateDamage(user, user, battle);

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testLightBallDoesNotBoostAttack() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        user.setPokedexEntry(PokedexEntry.PokedexEntryBuilder.createNewInstance().setPokedexNo(25).build());
        user.setHeldItem(LightBall.LIGHT_BALL);
        final int boostedDamage = move.calculateDamage(user, user, battle);

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testSTABIsNotApplied() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        user.setTypes(Set.of(Type.TYPELESS));
        final int boostedDamage = move.calculateDamage(user, user, battle);

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testCannotCrit() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        Main.CRIT_RANDOM = ZERO_RANDOM;
        final int boostedDamage = move.calculateDamage(user, user, battle);

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testDamageNotHalvedByBurn() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        user.setStatus(Status.BURN);
        final int boostedDamage = move.calculateDamage(user, user, battle);

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testLifeOrbDoesNotApply() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        user.setHeldItem(LifeOrb.LIFE_ORB);
        final int boostedDamage = move.calculateDamage(user, user, battle);

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testHelpingHandDoesNotApply() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        user.addVolatileStatus(new HelpingHandStatus());
        final int boostedDamage = move.calculateDamage(user, user, battle);

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testReflectDoesNotApply() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        battle.getCorrespondingTrainer(user).setReflectTurns(5);
        final int boostedDamage = move.calculateDamage(user, user, battle);

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testTypeBoostItemDoesNotApply() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        user.setHeldItem(TypeBoostItem.SILK_SCARF);
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.CONFUSED, 100));
        new Turn(user, new Tackle()).takeTurn(battle);
        final int boostedDamage = user.getHpDiff();

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testBypassesWonderGuard() {
        //TODO: Implement Wonder Guard
        assertThat(false).isTrue();
    }

    @Test
    void testAttackDoublingAbilityDoesNotApply() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        user.setAbility(AttackDoublingAbility.HUGE_POWER);
        final int boostedDamage = move.calculateDamage(user, user, battle);

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testTechnicianDoesNotApply() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        user.setAbility(Technician.TECHNICIAN);
        final int boostedDamage = move.calculateDamage(user, user, battle);

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testRivalryDoesNotApply() {
        final int baseDamage = move.calculateDamage(user, user, battle);

        user.setSex(Sex.FEMALE);
        user.setAbility(Rivalry.RIVALRY);
        final int boostedDamage = move.calculateDamage(user, user, battle);

        assertThat(baseDamage).isEqualTo(boostedDamage);
    }

    @Test
    void testPowerTrickApplies() {
        assertThat(false).isTrue();
    }

    @Test
    void testGuardSplitApplies() {
        assertThat(false).isTrue();
    }

    @Test
    void testPowerSplitApplies() {
        assertThat(false).isTrue();
    }

    @Test
    void testSturdyActivates() {
        user.setMaxHP(2);
        user.setCurrentHP(2);
        user.setAbility(Sturdy.STURDY);

        move.executeOnTarget(user, user, battle);
        final int damage = move.calculateDamage(user, user, battle);
        assertThat(damage).isGreaterThan(1);
        assertThat(user.getCurrentHP()).isOne();
        assertThat(user.hasFainted()).isFalse();
        assertThat(user.getStatus()).isEqualTo(Status.NONE);
    }

    @Test
    void testFocusSashActivates() {
        user.setMaxHP(2);
        user.setCurrentHP(2);
        user.setHeldItem(FocusSash.FOCUS_SASH);

        move.executeOnTarget(user, user, battle);
        final int damage = move.calculateDamage(user, user, battle);
        assertThat(damage).isGreaterThan(1);
        assertThat(user.getCurrentHP()).isOne();
        assertThat(user.hasFainted()).isFalse();
        assertThat(user.getStatus()).isEqualTo(Status.NONE);
        assertThat(user.getHeldItem()).isEqualTo(NoItem.NO_ITEM);
    }

    @Test
    void testFocusBandActivates() {
        user.setMaxHP(2);
        user.setCurrentHP(2);
        //    TODO:    user.setHeldItem(FocusBand.FOCUS);
        assertThat(false).isTrue();

        //        move.executeOnTarget(user, user, battle);
        //        final int damage = move.calculateDamage(user, user, battle);
        //        assertThat(damage).isGreaterThan(1);
        //        assertThat(user.getCurrentHP()).isOne();
        //        assertThat(user.hasFainted()).isFalse();
        //        assertThat(user.getStatus()).isEqualTo(Status.NONE);
        //        assertThat(user.getHeldItem()).isEqualTo(NoItem.NO_ITEM);
    }
}
