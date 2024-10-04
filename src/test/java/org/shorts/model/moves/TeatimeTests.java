package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.items.berries.Berry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.Unnerve.UNNERVE;
import static org.shorts.model.abilities.elementabsorb.DrawingAbility.LIGHTNING_ROD;
import static org.shorts.model.abilities.elementabsorb.ElementAbsorbRaiseStatAbility.MOTOR_DRIVE;
import static org.shorts.model.abilities.elementabsorb.ElementAbsorbingAbility.VOLT_ABSORB;
import static org.shorts.model.items.CellBattery.CELL_BATTERY;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.berries.OranBerry.ORAN_BERRY;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class TeatimeTests {

    private Teatime move;
    private Pokemon user;
    private Pokemon teammate;
    private Pokemon enemy1;
    private Pokemon enemy2;
    private Battle battle;

    @BeforeEach
    void setup() {
        move = new Teatime();
        user = getDummyPokemon();
        teammate = getDummyPokemon();
        enemy1 = getDummyPokemon();
        enemy2 = getDummyPokemon();
        user.setHeldItem(ORAN_BERRY);
        teammate.setHeldItem(ORAN_BERRY);
        enemy1.setHeldItem(ORAN_BERRY);
        enemy2.setHeldItem(ORAN_BERRY);

        battle = new DummyBattle(List.of(user, teammate), List.of(enemy1, enemy2), 2);
    }

    @Test
    void testAllPokemonEatBerriesEvenIfBerryHasNoEffect() {
        assertThat(user.isAtFullHP()).isTrue();
        assertThat(teammate.isAtFullHP()).isTrue();
        assertThat(enemy1.isAtFullHP()).isTrue();
        assertThat(enemy2.isAtFullHP()).isTrue();

        move.execute(user, battle.getPokemonWithinRange(user, move.getRange(user)), battle);

        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(teammate.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(enemy1.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(enemy2.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(teammate.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy1.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy2.getConsumedItem()).isEqualTo(ORAN_BERRY);
    }

    @Test
    void testBerryIsEatenEvenAboveThreshold() {
        user.takeDamage(10);
        teammate.takeDamage(10);
        enemy1.takeDamage(10);
        enemy2.takeDamage(10);

        assertThat(((Berry) user.getHeldItem()).isWithinThreshold(user)).isFalse();
        assertThat(((Berry) teammate.getHeldItem()).isWithinThreshold(teammate)).isFalse();
        assertThat(((Berry) enemy1.getHeldItem()).isWithinThreshold(enemy1)).isFalse();
        assertThat(((Berry) enemy2.getHeldItem()).isWithinThreshold(enemy2)).isFalse();

        move.execute(user, battle.getPokemonWithinRange(user, move.getRange(user)), battle);

        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(teammate.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(enemy1.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(enemy2.getHeldItem()).isEqualTo(NO_ITEM);

        assertThat(user.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(teammate.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy1.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy2.getConsumedItem()).isEqualTo(ORAN_BERRY);

        assertThat(user.isAtFullHP()).isTrue();
        assertThat(teammate.isAtFullHP()).isTrue();
        assertThat(enemy1.isAtFullHP()).isTrue();
        assertThat(enemy2.isAtFullHP()).isTrue();
    }

    @Test
    void testBypassesSubstitute() {
        enemy1.addVolatileStatus(new SubstituteStatus(100));

        move.execute(user, battle.getPokemonWithinRange(user, move.getRange(user)), battle);

        assertThat(enemy1.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(enemy1.getConsumedItem()).isEqualTo(ORAN_BERRY);
    }

    @Test
    void testDoesNotBypassSemiInvulnerable() {
        enemy2.addVolatileStatus(new VolatileStatus(VolatileStatusType.SEMI_INVULNERABLE, 1));

        move.execute(user, battle.getPokemonWithinRange(user, move.getRange(user)), battle);

        assertThat(enemy2.getHeldItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy2.getConsumedItem()).isEqualTo(NO_ITEM);
    }

    @Test
    void testIgnoresUnnerve() {
        user.setAbility(UNNERVE);
        enemy1.setAbility(UNNERVE);

        move.execute(user, battle.getPokemonWithinRange(user, move.getRange(user)), battle);

        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(teammate.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(enemy1.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(enemy2.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(teammate.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy1.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy2.getConsumedItem()).isEqualTo(ORAN_BERRY);
    }

    @Test
    void testIgnoresMagicRoom() {
        battle.setMagicRoomTurns(4);

        move.execute(user, battle.getPokemonWithinRange(user, move.getRange(user)), battle);

        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(teammate.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(enemy1.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(enemy2.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(teammate.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy1.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy2.getConsumedItem()).isEqualTo(ORAN_BERRY);
    }

    @Test
    void testElectrifiedTeatimeShouldBeAbsorbedByAbilityEvenWithNoBerryButDoesNotActivateCellBattery() {
        move.setType(Type.ELECTRIC);
        user.setHeldItem(CELL_BATTERY);
        teammate.setHeldItem(NO_ITEM);
        teammate.setAbility(LIGHTNING_ROD);
        enemy1.setAbility(VOLT_ABSORB);
        enemy1.setCurrentHP(enemy1.getMaxHP() * 3 / 4);
        enemy2.setAbility(MOTOR_DRIVE);

        move.execute(user, battle.getPokemonWithinRange(user, move.getRange(user)), battle);

        assertThat(user.getHeldItem()).isEqualTo(CELL_BATTERY);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(teammate.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(teammate.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(enemy1.getHeldItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy1.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(enemy2.getHeldItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy2.getConsumedItem()).isEqualTo(NO_ITEM);

        assertThat(user.getStageAttack()).isZero();
        assertThat(teammate.getStageSpecialAttack()).isOne();
        assertThat(enemy1.isAtFullHP()).isTrue();
        assertThat(enemy2.getStageSpeed()).isOne();
    }
}
