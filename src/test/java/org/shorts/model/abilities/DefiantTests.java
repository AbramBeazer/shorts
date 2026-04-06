package org.shorts.model.abilities;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.model.moves.CloseCombat;
import org.shorts.model.moves.Crunch;
import org.shorts.model.moves.Growl;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.model.items.NoItem.*;
import static org.shorts.model.items.WhiteHerb.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class DefiantTests {

    private Pokemon user;
    private Pokemon ally;
    private Pokemon opponent;
    private Pokemon otherOpponent;
    private Battle battle;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        user.setAbility(Defiant.DEFIANT);
        ally = getDummyPokemon();
        opponent = getDummyPokemon();
        otherOpponent = getDummyPokemon();
        battle = new DummyBattle(List.of(user, ally), List.of(opponent, otherOpponent), 2);
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testAttackRaisesTwoStagesAfterDropFromOpponent() {
        assertThat(user.getStageAttack()).isZero();

        new Turn(opponent, new Growl(), 0).takeTurn(battle);
        assertThat(user.getStageAttack()).isOne();

        new Turn(opponent, new Crunch(), 0).takeTurn(battle);
        assertThat(user.getStageDefense()).isEqualTo(-1);
        assertThat(user.getStageAttack()).isEqualTo(3);
    }

    @Test
    void testAttackNotRaisedIfDropsOwnStats() {
        assertThat(user.getStageAttack()).isZero();
        assertThat(user.getStageDefense()).isZero();
        assertThat(user.getStageSpecialDefense()).isZero();

        new Turn(user, new CloseCombat(), 0).takeTurn(battle);
        assertThat(user.getStageDefense()).isEqualTo(-1);
        assertThat(user.getStageSpecialDefense()).isEqualTo(-1);
        assertThat(user.getStageAttack()).isZero();
    }

    @Test
    void testAttackNotRaisedIfStatsDroppedByAlly() {
        assertThat(user.getStageAttack()).isZero();

        new Turn(ally, new Growl(), 2).takeTurn(battle);
        assertThat(user.getStageAttack()).isEqualTo(-1);
    }

    @Test
    void testWhiteHerbDoesNotActivateAfterDefiant() {
        assertThat(user.getStageAttack()).isZero();

        user.setHeldItem(WHITE_HERB);
        new Turn(opponent, new Growl(), 0).takeTurn(battle);
        assertThat(user.getStageAttack()).isOne();
        assertThat(user.getHeldItem()).isEqualTo(WHITE_HERB);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);
    }

    @Test
    void testWhiteHerbActivatesForStatDroppedByAlly() {
        assertThat(user.getStageAttack()).isZero();

        user.setHeldItem(WHITE_HERB);
        new Turn(ally, new Growl(), 2).takeTurn(battle);
        assertThat(user.getStageAttack()).isZero();
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(WHITE_HERB);
    }

    @Test
    void testWhiteHerbActivatesForStatDroppedBySelf() {
        assertThat(user.getStageAttack()).isZero();
        assertThat(user.getStageDefense()).isZero();
        assertThat(user.getStageSpecialDefense()).isZero();

        user.setHeldItem(WHITE_HERB);
        new Turn(user, new CloseCombat(), 0).takeTurn(battle);
        assertThat(user.getStageDefense()).isZero();
        assertThat(user.getStageSpecialDefense()).isZero();
        assertThat(user.getStageAttack()).isZero();
    }

    @Test
    void testLowersMultipleStats() {
        assertThat(false).isTrue();
    }

    @Test
    void testLowersMultipleStatsWithWhiteHerb() {
        assertThat(false).isTrue();
    }
}
