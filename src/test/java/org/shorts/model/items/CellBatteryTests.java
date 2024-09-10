package org.shorts.model.items;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.ThunderFang;
import org.shorts.model.moves.ThunderWave;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.Contrary.CONTRARY;
import static org.shorts.model.abilities.elementabsorb.DrawingAbility.LIGHTNING_ROD;
import static org.shorts.model.abilities.elementabsorb.ElementAbsorbRaiseStatAbility.MOTOR_DRIVE;
import static org.shorts.model.items.CellBattery.CELL_BATTERY;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.types.Type.GROUND;

class CellBatteryTests {

    private Pokemon holder;
    private Pokemon opponent;
    private Battle battle;
    private Move move;

    @BeforeEach
    void setup() {
        holder = getDummyPokemon();
        holder.setHeldItem(CELL_BATTERY);
        holder.setStageAttack(0);
        opponent = getDummyPokemon();
        battle = new DummyBattle();
        move = new ThunderFang();
    }

    @Test
    void testActivatesWhenHitByElectricMove() {
        assertThat(holder.getStageAttack()).isZero();
        move.execute(opponent, List.of(holder), battle);
        assertThat(holder.getStageAttack()).isOne();
        assertThat(holder.getConsumedItem()).isEqualTo(CELL_BATTERY);
        assertThat(holder.getHeldItem()).isEqualTo(NO_ITEM);
    }

    @Test
    void testDoesNotActivateForStatusMoves() {
        assertThat(holder.getStageAttack()).isZero();
        new ThunderWave().execute(opponent, List.of(holder), battle);
        assertThat(holder.getStageAttack()).isZero();
        assertThat(holder.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(holder.getHeldItem()).isEqualTo(CELL_BATTERY);
    }

    @Test
    void testDoesNotForGroundTypes() {
        holder.setTypes(Set.of(GROUND));

        assertThat(holder.getStageAttack()).isZero();
        move.execute(opponent, List.of(holder), battle);
        assertThat(holder.getStageAttack()).isZero();
        assertThat(holder.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(holder.getHeldItem()).isEqualTo(CELL_BATTERY);
    }

    @Test
    void testDoesNotActivateForMotorDriveUsers() {
        holder.setAbility(MOTOR_DRIVE);

        assertThat(holder.getStageAttack()).isZero();
        move.execute(opponent, List.of(holder), battle);
        assertThat(holder.getStageAttack()).isZero();
        assertThat(holder.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(holder.getHeldItem()).isEqualTo(CELL_BATTERY);
    }

    @Test
    void testDoesNotActivateForLightningRodUsers() {
        holder.setAbility(LIGHTNING_ROD);

        assertThat(holder.getStageAttack()).isZero();
        move.execute(opponent, List.of(holder), battle);
        assertThat(holder.getStageAttack()).isZero();
        assertThat(holder.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(holder.getHeldItem()).isEqualTo(CELL_BATTERY);
    }

    @Test
    void testDoesNotActivateIfAttackCannotBeChanged() {
        holder.setStageAttack(6);

        move.execute(opponent, List.of(holder), battle);
        assertThat(holder.getStageAttack()).isEqualTo(6);
        assertThat(holder.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(holder.getHeldItem()).isEqualTo(CELL_BATTERY);

        holder.setStageAttack(-6);
        holder.setAbility(CONTRARY);

        move.execute(opponent, List.of(holder), battle);
        assertThat(holder.getStageAttack()).isEqualTo(-6);
        assertThat(holder.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(holder.getHeldItem()).isEqualTo(CELL_BATTERY);
    }
}
