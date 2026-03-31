package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.abilities.Technician;
import org.shorts.model.moves.healthdependent.DragonEnergy;
import org.shorts.model.moves.healthdependent.Eruption;
import org.shorts.model.moves.healthdependent.WaterSpout;
import org.shorts.model.moves.multihit.MultiHitMove;
import org.shorts.model.moves.priority.plusOne.QuickAttack;
import org.shorts.model.pokemon.PokedexEntry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.model.items.FocusSash.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class WeakMoveTeraBoostTests {

    private static final int SIXTY = 60;

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        user.setTeraType(Type.NORMAL);
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testTeraBoostsWeakMovesOfTeraTypeTo60BasePower() {
        final Move move = new Tackle();
        assertThat(move.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        user.terastallize();
        assertThat(move.calculateMovePower(user, target, battle)).isEqualTo(SIXTY);
    }

    @Test
    void testDoesNotBoostMovesNotOfTeraType() {
        final Move move = new Ember();
        assertThat(move.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        user.terastallize();
        assertThat(move.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
    }

    @Test
    void testDoesNotLowerTechnicianBoostedMoveToSixty() {
        final Move move = new TerrainPulse();
        assertThat(move.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        user.setAbility(Technician.TECHNICIAN);
        assertThat(move.calculateMovePower(user, target, battle)).isGreaterThan(SIXTY);
        user.terastallize();
        assertThat(move.calculateMovePower(user, target, battle)).isGreaterThan(SIXTY);
    }

    @Test
    void testNoBoostForMultiHitMoves() {
        final Move move = new MultiHitMove(
            "test two hits",
            20,
            100,
            Type.NORMAL,
            Move.Category.PHYSICAL,
            Range.NORMAL,
            8,
            true,
            0,
            2,
            5) {

        };
        assertThat(move.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        user.terastallize();
        assertThat(move.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
    }

    @Test
    void testNoBoostForPriorityMoves() {
        final Move move = new QuickAttack();
        assertThat(move.getPriority(user, battle)).isPositive();
        assertThat(move.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        user.terastallize();
        assertThat(move.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
    }

    @Test
    void testNoBoostForVariablePower() {
        final Move magnitude = new Magnitude();

        target.setSpeed((int) (user.calculateSpeed(battle) + 1));
        final Move gyroBall = new GyroBall();
        final Move electroBall = new ElectroBall();

        final PokedexEntry oneKilo = PokedexEntry.PokedexEntryBuilder.createNewInstance().setWeight(1).build();
        user.setPokedexEntry(oneKilo);
        target.setPokedexEntry(oneKilo);
        final Move heatCrash = new HeatCrash();
        final Move heavySlam = new HeavySlam();
        final Move lowKick = new LowKick();
        final Move grassKnot = new GrassKnot();

        user.setCurrentHP(1);
        final Move dragonEnergy = new DragonEnergy();
        final Move eruption = new Eruption();
        final Move waterSpout = new WaterSpout();

        user.setHeldItem(FOCUS_SASH);
        final Move fling = new Fling();

        testVariablePowerMove(magnitude);
        testVariablePowerMove(gyroBall);
        testVariablePowerMove(electroBall);
        testVariablePowerMove(heatCrash);
        testVariablePowerMove(heavySlam);
        testVariablePowerMove(lowKick);
        testVariablePowerMove(grassKnot);
        testVariablePowerMove(dragonEnergy);
        testVariablePowerMove(eruption);
        testVariablePowerMove(waterSpout);
        testVariablePowerMove(fling);

        user.terastallize();
        testVariablePowerMove(magnitude);
        testVariablePowerMove(gyroBall);
        testVariablePowerMove(electroBall);
        testVariablePowerMove(heatCrash);
        testVariablePowerMove(heavySlam);
        testVariablePowerMove(lowKick);
        testVariablePowerMove(grassKnot);
        testVariablePowerMove(dragonEnergy);
        testVariablePowerMove(eruption);
        testVariablePowerMove(waterSpout);
        testVariablePowerMove(fling);
    }

    private void testVariablePowerMove(Move move) {
        user.setTeraType(move.getType());
        assertThat(move.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
    }
}
