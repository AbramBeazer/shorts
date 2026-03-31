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

        assertThat(magnitude.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(gyroBall.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(electroBall.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(heatCrash.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(heavySlam.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(lowKick.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(grassKnot.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(dragonEnergy.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(eruption.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(waterSpout.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(fling.calculateMovePower(user, target, battle)).isLessThan(SIXTY);

        user.terastallize();
        assertThat(magnitude.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(gyroBall.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(electroBall.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(heatCrash.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(heavySlam.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(lowKick.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(grassKnot.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(dragonEnergy.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(eruption.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(waterSpout.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
        assertThat(fling.calculateMovePower(user, target, battle)).isLessThan(SIXTY);
    }
}
