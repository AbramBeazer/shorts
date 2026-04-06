package org.shorts.model.abilities;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.model.StatEnum;
import org.shorts.model.moves.CloseCombat;
import org.shorts.model.moves.Crunch;
import org.shorts.model.moves.StruggleBug;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.moves.ShellSmash;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.model.items.NoItem.*;
import static org.shorts.model.items.WhiteHerb.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class CompetitiveTests {

    private Pokemon user;
    private Pokemon ally;
    private Pokemon opponent;
    private Pokemon otherOpponent;
    private Battle battle;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        user.setAbility(Competitive.COMPETITIVE);
        ally = getDummyPokemon();
        opponent = getDummyPokemon();
        otherOpponent = getDummyPokemon();
        battle = new DummyBattle(List.of(user, ally), List.of(opponent, otherOpponent), 2);
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testSpAtkRaisesTwoStagesAfterDropFromOpponent() {
        assertThat(user.getStageSpecialAttack()).isZero();

        new Turn(opponent, new StruggleBug(), 0).takeTurn(battle);
        assertThat(user.getStageSpecialAttack()).isOne();
        user.fullRestore();

        new Turn(opponent, new Crunch(), 0).takeTurn(battle);
        assertThat(user.getStageDefense()).isEqualTo(-1);
        assertThat(user.getStageSpecialAttack()).isEqualTo(3);
    }

    @Test
    void testSpAtkNotRaisedIfDropsOwnStats() {
        assertThat(user.getStageSpecialAttack()).isZero();
        assertThat(user.getStageDefense()).isZero();
        assertThat(user.getStageSpecialDefense()).isZero();

        new Turn(user, new CloseCombat(), 0).takeTurn(battle);
        assertThat(user.getStageDefense()).isEqualTo(-1);
        assertThat(user.getStageSpecialDefense()).isEqualTo(-1);
        assertThat(user.getStageSpecialAttack()).isZero();
    }

    @Test
    void testSpAtkNotRaisedIfStatsDroppedByAlly() {
        assertThat(user.getStageSpecialAttack()).isZero();

        new Turn(ally, new StruggleBug(), 2).takeTurn(battle);
        assertThat(user.getStageSpecialAttack()).isEqualTo(-1);
    }

    @Test
    void testWhiteHerbDoesNotActivateIfCompetitiveHasAlreadyRestoredStat() {
        assertThat(user.getStageSpecialAttack()).isZero();

        user.setHeldItem(WHITE_HERB);
        new Turn(opponent, new StruggleBug(), 0).takeTurn(battle);
        assertThat(user.getStageSpecialAttack()).isOne();
        assertThat(user.getHeldItem()).isEqualTo(WHITE_HERB);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);
    }

    @Test
    void testCompetitiveDoesNotActivateButWhiteHerbActivatesForStatDroppedByAlly() {
        assertThat(user.getStageSpecialAttack()).isZero();

        user.setHeldItem(WHITE_HERB);
        new Turn(ally, new StruggleBug(), 2).takeTurn(battle);
        assertThat(user.getStageSpecialAttack()).isZero();
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(WHITE_HERB);
    }

    @Test
    void testCompetitiveDoesNotActivateButWhiteHerbActivatesForStatDroppedBySelf() {
        assertThat(user.getStageSpecialAttack()).isZero();
        assertThat(user.getStageDefense()).isZero();
        assertThat(user.getStageSpecialDefense()).isZero();

        user.setHeldItem(WHITE_HERB);
        new Turn(user, new CloseCombat(), 0).takeTurn(battle);
        assertThat(user.getStageDefense()).isZero();
        assertThat(user.getStageSpecialDefense()).isZero();
        assertThat(user.getStageSpecialAttack()).isZero();
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(WHITE_HERB);
    }

    @Test
    void testLowersMultipleStats() {
        assertThat(user.getStageAttack()).isZero();
        assertThat(user.getStageSpecialAttack()).isZero();

        new Turn(opponent, new LowerThreeStats(), 0).takeTurn(battle);
        assertThat(user.getStageAttack()).isEqualTo(-2);
        assertThat(user.getStageSpecialAttack()).isEqualTo(4);
        assertThat(user.getStageSpeed()).isEqualTo(-2);
    }

    @Test
    void testLowersMultipleStatsWithWhiteHerb() {
        assertThat(user.getStageAttack()).isZero();
        assertThat(user.getStageSpecialAttack()).isZero();

        user.setHeldItem(WHITE_HERB);
        new Turn(opponent, new LowerThreeStats(), 0).takeTurn(battle);
        assertThat(user.getStageAttack()).isZero();
        assertThat(user.getStageSpecialAttack()).isEqualTo(4);
        assertThat(user.getStageSpeed()).isZero();
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(WHITE_HERB);
    }

    @Test
    void testShellSmashDoesNotActivateCompetitive() {
        assertThat(user.getStageAttack()).isZero();
        assertThat(user.getStageDefense()).isZero();
        assertThat(user.getStageSpecialAttack()).isZero();
        assertThat(user.getStageSpecialDefense()).isZero();
        assertThat(user.getStageSpeed()).isZero();

        new Turn(user, new ShellSmash()).takeTurn(battle);
        assertThat(user.getStageAttack()).isEqualTo(2);
        assertThat(user.getStageSpecialAttack()).isEqualTo(2);
        assertThat(user.getStageSpeed()).isEqualTo(2);
        assertThat(user.getStageDefense()).isEqualTo(-1);
        assertThat(user.getStageSpecialDefense()).isEqualTo(-1);
    }

    @Test
    void testShellSmashDoesNotActivateCompetitiveButActivatesWhiteHerb() {
        assertThat(user.getStageAttack()).isZero();
        assertThat(user.getStageDefense()).isZero();
        assertThat(user.getStageSpecialAttack()).isZero();
        assertThat(user.getStageSpecialDefense()).isZero();
        assertThat(user.getStageSpeed()).isZero();

        user.setHeldItem(WHITE_HERB);
        new Turn(user, new ShellSmash()).takeTurn(battle);
        assertThat(user.getStageAttack()).isEqualTo(2);
        assertThat(user.getStageSpecialAttack()).isEqualTo(2);
        assertThat(user.getStageSpeed()).isEqualTo(2);
        assertThat(user.getStageDefense()).isZero();
        assertThat(user.getStageSpecialDefense()).isZero();
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(WHITE_HERB);
    }

    private static class LowerThreeStats extends Move {

        public LowerThreeStats() {
            super("Lower Three Stats", 0, -1, Type.NORMAL, Category.STATUS, Range.NORMAL, 10, false, 100);
        }

        @Override
        protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
            target.changeStat(battle, user, -2, StatEnum.ATK, StatEnum.SPATK, StatEnum.SPEED);
        }
    }
}
