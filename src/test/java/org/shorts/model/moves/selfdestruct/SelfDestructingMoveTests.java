package org.shorts.model.moves.selfdestruct;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.MockRandomReturnMax;
import org.shorts.MockRandomReturnZero;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Terrain;
import org.shorts.model.abilities.Damp;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;

import static org.shorts.model.pokemon.PokemonTestUtils.*;

class SelfDestructingMoveTests {

    private Pokemon boomer;
    private Pokemon teammate;
    private Pokemon opp1;
    private Pokemon opp2;
    private Battle battle;
    private SelfDestructingMove move;

    @BeforeEach
    void setUp() {
        Main.HIT_RANDOM = MockRandomReturnZero.ZERO_RANDOM;
        battle = new DummyBattle();
        boomer = getDummyPokemon();
        teammate = getDummyPokemon();
        opp1 = getDummyPokemon();
        opp2 = getDummyPokemon();
        move = new SelfDestructingMove("TEST BOOM", 200, Type.NORMAL, Move.Category.PHYSICAL) {

        };
    }

    @Test
    void testFailsWhenDampIsPresent() {
        opp1.setAbility(Damp.DAMP);

        move.execute(boomer, List.of(teammate, opp1, opp2), battle);

        assertThat(boomer.hasFainted()).isFalse();
        assertThat(teammate.isAtFullHP()).isTrue();
        assertThat(opp1.isAtFullHP()).isTrue();
        assertThat(opp2.isAtFullHP()).isTrue();
        assertThat(move.getCurrentPP()).isLessThan(move.getMaxPP());
        assertThat(boomer.getLastMoveUsed()).isEqualTo(move);
    }

    @Test
    void testFailsWhenAllTargetsAreImmune() {
        Set<Type> types = Set.of(Type.GHOST);
        teammate.setTypes(types);
        opp1.setTypes(types);
        opp2.setTypes(types);

        move.execute(boomer, List.of(teammate, opp1, opp2), battle);

        assertThat(boomer.hasFainted()).isFalse();
        assertThat(teammate.isAtFullHP()).isTrue();
        assertThat(opp1.isAtFullHP()).isTrue();
        assertThat(opp2.isAtFullHP()).isTrue();
        assertThat(move.getCurrentPP()).isLessThan(move.getMaxPP());
        assertThat(boomer.getLastMoveUsed()).isEqualTo(move);
    }

    @Test
    void testSucceedsWhenAtLeastOneTargetIsNotImmune() {
        Set<Type> types = Set.of(Type.GHOST);
        opp1.setTypes(types);
        opp2.setTypes(types);

        move.execute(boomer, List.of(teammate, opp1, opp2), battle);

        assertThat(boomer.hasFainted()).isTrue();
        assertThat(teammate.isAtFullHP()).isFalse();
        assertThat(opp1.isAtFullHP()).isTrue();
        assertThat(opp2.isAtFullHP()).isTrue();
        assertThat(move.getCurrentPP()).isLessThan(move.getMaxPP());
        assertThat(boomer.getLastMoveUsed()).isEqualTo(move);
    }

    @Test
    void testUserFaintsWithNoTargets() {
        move.execute(boomer, List.of(), battle);

        assertThat(boomer.hasFainted()).isTrue();
        assertThat(move.getCurrentPP()).isLessThan(move.getMaxPP());
        assertThat(boomer.getLastMoveUsed()).isEqualTo(move);
    }

    @Test
    void testUserDoesNotFaintIfMoveMisses() {
        Main.HIT_RANDOM = MockRandomReturnMax.MAX_RANDOM;

        move.execute(boomer, List.of(teammate, opp1, opp2), battle);

        assertThat(boomer.hasFainted()).isFalse();
        assertThat(teammate.isAtFullHP()).isTrue();
        assertThat(opp1.isAtFullHP()).isTrue();
        assertThat(opp2.isAtFullHP()).isTrue();
        assertThat(move.getCurrentPP()).isLessThan(move.getMaxPP());
        assertThat(boomer.getLastMoveUsed()).isEqualTo(move);
    }

    @Test
    void testMistyExplosionPowerBoostOnMistyTerrain() {
        assertThat(boomer.isGrounded()).isTrue();
        MistyExplosion mistyExplosion = new MistyExplosion();

        assertThat(mistyExplosion.getPowerMultipliers(boomer, opp1, battle)).isOne();

        battle.setTerrain(Terrain.MISTY, 5);

        assertThat(mistyExplosion.getPowerMultipliers(boomer, opp1, battle)).isEqualTo(MistyExplosion.MULTIPLIER);
    }
}
