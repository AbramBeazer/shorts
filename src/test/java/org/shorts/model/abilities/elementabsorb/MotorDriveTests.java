package org.shorts.model.abilities.elementabsorb;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.ThunderFang;
import org.shorts.model.moves.ThunderWave;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.items.CellBattery.CELL_BATTERY;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.status.Status.NONE;

class MotorDriveTests {

    private ElementAbsorbRaiseStatAbility ability = ElementAbsorbRaiseStatAbility.MOTOR_DRIVE;
    private Pokemon mdMon;
    private Pokemon other;
    private Battle battle;
    private Move move;

    @BeforeEach
    void setup() {
        mdMon = getDummyPokemon();
        mdMon.setAbility(ability);
        mdMon.setStageSpeed(0);
        other = getDummyPokemon();
        battle = new DummySingleBattle();
        move = new ThunderFang();
    }

    @Test
    void testNoDamageFromElectricMoves() {
        assertThat(mdMon.beforeHit(other, battle, move)).isZero();
        move.execute(other, List.of(mdMon), battle);
        assertThat(mdMon.getMaxHP()).isEqualTo(mdMon.getCurrentHP());
    }

    @Test
    void testSpeedBoostedAfterHit() {
        assertThat(mdMon.getStageSpeed()).isZero();
        move.execute(other, List.of(mdMon), battle);
        assertThat(mdMon.getStageSpeed()).isOne();
    }

    @Test
    void testDoesNotActivateIfProtected() {
        assertThat(mdMon.getStageSpeed()).isZero();
        mdMon.addVolatileStatus(new VolatileStatus(VolatileStatusType.PROTECTED, 1));
        move.execute(other, List.of(mdMon), battle);
        assertThat(mdMon.getStageSpeed()).isZero();
    }

    @Test
    void testActivatesWhenHitByStatusMove() {
        assertThat(mdMon.getStageSpeed()).isZero();
        new ThunderWave().execute(other, List.of(mdMon), battle);
        assertThat(mdMon.getStageSpeed()).isOne();
        assertThat(mdMon.getStatus()).isEqualTo(NONE);
    }

    @Test
    void testActivatesWithoutConsumingCellBattery() {
        mdMon.setHeldItem(CELL_BATTERY);

        assertThat(mdMon.getStageSpeed()).isZero();
        move.execute(other, List.of(mdMon), battle);
        assertThat(mdMon.getStageSpeed()).isOne();

        assertThat(mdMon.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(mdMon.getHeldItem()).isEqualTo(CELL_BATTERY);
    }

    @Test
    void testOnlyActivatedOnceByMultiHitMove() {
        assertThat(false).isTrue();
    }
}
