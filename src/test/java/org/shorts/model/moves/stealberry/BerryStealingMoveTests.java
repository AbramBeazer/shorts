package org.shorts.model.moves.stealberry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.items.HeldItem;
import org.shorts.model.items.Leftovers;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.abilities.Klutz.KLUTZ;
import static org.shorts.model.abilities.StickyHold.STICKY_HOLD;
import static org.shorts.model.abilities.Unnerve.UNNERVE;
import static org.shorts.model.items.AirBalloon.AIR_BALLOON;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.berries.JabocaBerry.JABOCA_BERRY;
import static org.shorts.model.items.berries.OranBerry.ORAN_BERRY;
import static org.shorts.model.items.berries.PinchBerry.LIECHI_BERRY;
import static org.shorts.model.items.berries.SitrusBerry.SITRUS_BERRY;
import static org.shorts.model.items.berries.typeresist.TypeResistBerry.CHILAN_BERRY;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class BerryStealingMoveTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private final HeldItem originalUserItem = Leftovers.LEFTOVERS;
    private final HeldItem originalUserConsumed = AIR_BALLOON;

    private final BerryStealingMove move = new BerryStealingMove(
        "TEST BERRY STEALING MOVE",
        1,
        100,
        Type.NORMAL,
        Move.Category.PHYSICAL,
        Range.NORMAL_SINGLE_ADJACENT_ANY,
        Integer.MAX_VALUE,
        true,
        100) {

    };

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        user.setHeldItem(originalUserItem);
        user.setConsumedItem(originalUserConsumed);
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
    }

    @Test
    void testUserStealsAndEatsBerry() {
        target.setHeldItem(ORAN_BERRY);
        user.setCurrentHP(user.getMaxHP() - 10);
        int damage = move.calculateDamage(user, target, battle);
        move.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP() - damage);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP());
        assertThat(user.getHeldItem()).isEqualTo(originalUserItem);
        assertThat(user.getConsumedItem()).isEqualTo(originalUserConsumed);
    }

    @Test
    void testBerryIsNotStolenIfSubIsHit() {
        target.setHeldItem(ORAN_BERRY);
        target.addVolatileStatus(new SubstituteStatus(1));
        user.setCurrentHP(user.getMaxHP() - 10);

        move.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
        assertThat(target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)).isFalse();
        assertThat(target.getHeldItem()).isEqualTo(ORAN_BERRY);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() - 10);
        assertThat(user.getHeldItem()).isEqualTo(originalUserItem);
        assertThat(user.getConsumedItem()).isEqualTo(originalUserConsumed);
    }

    @Test
    void testBerryNotStolenIfTargetHasStickyHold() {
        target.setHeldItem(ORAN_BERRY);
        target.setAbility(STICKY_HOLD);
        user.setCurrentHP(user.getMaxHP() - 10);

        int damage = move.calculateDamage(user, target, battle);
        move.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP() - damage);
        assertThat(target.getHeldItem()).isEqualTo(ORAN_BERRY);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() - 10);
        assertThat(user.getHeldItem()).isEqualTo(originalUserItem);
        assertThat(user.getConsumedItem()).isEqualTo(originalUserConsumed);
    }

    @Test
    void testTargetEatsJabocaBerryBeforeUserStealsIt() {
        target.setHeldItem(JABOCA_BERRY);
        move.executeOnTarget(user, target, battle);
        final int damage = target.getMaxHP() - target.getCurrentHP();

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP() - damage);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(target.getConsumedItem()).isEqualTo(JABOCA_BERRY);
        assertThat(user.getCurrentHP()).isEqualTo(Math.round(user.getMaxHP() * .875));
        assertThat(user.getHeldItem()).isEqualTo(originalUserItem);
        assertThat(user.getConsumedItem()).isEqualTo(originalUserConsumed);
    }

    @Test
    void testTargetEatsTypeResistBerryBeforeUserStealsIt() {
        move.executeOnTarget(user, target, battle);
        final int baseDamage = target.getMaxHP() - target.getCurrentHP();
        target.fullRestore();

        target.setHeldItem(CHILAN_BERRY);
        move.executeOnTarget(user, target, battle);
        final int reducedDamage = target.getMaxHP() - target.getCurrentHP();

        assertThat(reducedDamage).isEqualTo(baseDamage / 2);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(target.getConsumedItem()).isEqualTo(CHILAN_BERRY);
        assertThat(user.getHeldItem()).isEqualTo(originalUserItem);
        assertThat(user.getConsumedItem()).isEqualTo(originalUserConsumed);
    }

    @Test
    void testUserStealsAndEatsPinchBerryEvenIfTargetIsWithinThresholdAfterDamage() {
        target.setHeldItem(LIECHI_BERRY);
        final int justAboveThreshold = (target.getMaxHP() / 2) + 1;
        target.setCurrentHP(justAboveThreshold);

        int damage = move.calculateDamage(user, target, battle);
        move.executeOnTarget(user, target, battle);

        assertThat(target.hasFainted()).isFalse();
        assertThat(target.getCurrentHP()).isEqualTo(justAboveThreshold - damage);
        assertThat(target.getStageAttack()).isZero();
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getStageAttack()).isOne();
        assertThat(user.getHeldItem()).isEqualTo(originalUserItem);
        assertThat(user.getConsumedItem()).isEqualTo(originalUserConsumed);
        //TODO: When the user has Ripen, does the stat still get raised two stages?
    }

    @Test
    void testUserStealsAndEatsSitrusBerryEvenIfTargetIsWithinThresholdAfterDamage() {
        target.setHeldItem(SITRUS_BERRY);
        final int justAboveThreshold = (target.getMaxHP() / 2) + 1;
        target.setCurrentHP(justAboveThreshold);
        user.takeDamage(10);

        int damage = move.calculateDamage(user, target, battle);
        move.executeOnTarget(user, target, battle);

        assertThat(target.hasFainted()).isFalse();
        assertThat(target.getCurrentHP()).isEqualTo(justAboveThreshold - damage);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP());
        assertThat(user.getHeldItem()).isEqualTo(originalUserItem);
        assertThat(user.getConsumedItem()).isEqualTo(originalUserConsumed);
    }

    @Test
    void testUserWithKlutzStealsAndEatsBerry() {
        target.setHeldItem(ORAN_BERRY);
        user.setAbility(KLUTZ);
        user.setCurrentHP(user.getMaxHP() - 10);

        int damage = move.calculateDamage(user, target, battle);
        move.executeOnTarget(user, target, battle);

        assertThat(target.hasFainted()).isFalse();
        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP() - damage);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP());
        assertThat(user.getHeldItem()).isEqualTo(originalUserItem);
        assertThat(user.getConsumedItem()).isEqualTo(originalUserConsumed);
    }

    @Test
    void testUserStealsAndEatsBerryWhenTargetHasUnnerve() {
        target.setHeldItem(ORAN_BERRY);
        target.setAbility(UNNERVE);
        user.setCurrentHP(user.getMaxHP() - 10);

        int damage = move.calculateDamage(user, target, battle);
        move.executeOnTarget(user, target, battle);

        assertThat(target.hasFainted()).isFalse();
        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP() - damage);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP());
        assertThat(user.getHeldItem()).isEqualTo(originalUserItem);
        assertThat(user.getConsumedItem()).isEqualTo(originalUserConsumed);
    }

    @Test
    void testUserUnderEmbargoStealsAndEatsBerry() {
        target.setHeldItem(ORAN_BERRY);
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.EMBARGOED, 5));
        user.setCurrentHP(user.getMaxHP() - 10);

        int damage = move.calculateDamage(user, target, battle);
        move.executeOnTarget(user, target, battle);

        assertThat(target.hasFainted()).isFalse();
        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP() - damage);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP());
        assertThat(user.getHeldItem()).isEqualTo(originalUserItem);
        assertThat(user.getConsumedItem()).isEqualTo(originalUserConsumed);
    }
}
