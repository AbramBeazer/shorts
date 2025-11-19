package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.model.items.Leftovers.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class HazeTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private Move haze;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        haze = new Haze();
        user.setStageAttack(6);
        user.setStageDefense(5);
        user.setStageSpecialAttack(4);
        user.setStageSpecialDefense(3);
        user.setStageSpeed(2);
        user.setStageEvasion(1);
        user.setStageAccuracy(-1);
        target.setStageAttack(-2);
        target.setStageDefense(-3);
        target.setStageSpecialAttack(-4);
        target.setStageSpecialDefense(-5);
        target.setStageSpeed(-6);
    }

    @Test
    void testHazeResetsStatStagesToZero() {
        new Turn(user, haze).takeTurn(battle);
        assertThat(user.getStageAttack()).isZero();
        assertThat(user.getStageDefense()).isZero();
        assertThat(user.getStageSpecialAttack()).isZero();
        assertThat(user.getStageSpecialDefense()).isZero();
        assertThat(user.getStageSpeed()).isZero();
        assertThat(user.getStageEvasion()).isZero();
        assertThat(user.getStageAccuracy()).isZero();
        assertThat(target.getStageAttack()).isZero();
        assertThat(target.getStageDefense()).isZero();
        assertThat(target.getStageSpecialAttack()).isZero();
        assertThat(target.getStageSpecialDefense()).isZero();
        assertThat(target.getStageSpeed()).isZero();
        assertThat(target.getStageEvasion()).isZero();
        assertThat(target.getStageAccuracy()).isZero();
    }
}
