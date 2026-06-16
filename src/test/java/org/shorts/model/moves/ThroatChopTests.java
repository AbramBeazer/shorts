package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class ThroatChopTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private Move throatChop;
    private Move soundMove;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        throatChop = new ThroatChop();
        soundMove = new MockSoundMove();
    }

    @Test
    void testThroatChopAppliesStatusForTwoTurns() {
        throatChop.executeOnTarget(user, target, battle);
        assertThat(target.hasVolatileStatus(VolatileStatusType.THROAT_CHOPPED)).isTrue();
        battle.endOfTurn();
        assertThat(target.hasVolatileStatus(VolatileStatusType.THROAT_CHOPPED)).isTrue();
        battle.endOfTurn();
        assertThat(target.hasVolatileStatus(VolatileStatusType.THROAT_CHOPPED)).isFalse();
    }

    @Test
    void testMultipleUsesDoesNotResetCounter() {
        throatChop.executeOnTarget(user, target, battle);
        assertThat(target.hasVolatileStatus(VolatileStatusType.THROAT_CHOPPED)).isTrue();

        battle.endOfTurn();
        assertThat(target.hasVolatileStatus(VolatileStatusType.THROAT_CHOPPED)).isTrue();
        assertThat(target.getVolatileStatus(VolatileStatusType.THROAT_CHOPPED).getTurnsRemaining()).isOne();

        throatChop.executeOnTarget(user, target, battle);
        assertThat(target.hasVolatileStatus(VolatileStatusType.THROAT_CHOPPED)).isTrue();
        assertThat(target.getVolatileStatus(VolatileStatusType.THROAT_CHOPPED).getTurnsRemaining()).isOne();
    }

    @Test
    void testThroatChopStatusPreventsSoundMoves() {
        target.addVolatileStatus(VolatileStatusType.THROAT_CHOPPED, 2);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(target.getStatus()).isEqualTo(Status.NONE);

        new Turn(target, soundMove).takeTurn(battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(target.getStatus()).isEqualTo(Status.NONE);
    }

    @Test
    void testThroatChopEndsUproar() {

        assertThat(false).isTrue();
    }

    @Test
    void testSoundMoveFailsIfThroatChopGoesFirst() {
        user.setSpeed(1000);
        battle.takeTurns(List.of(new Turn(user, throatChop), new Turn(target, soundMove)));

        assertThat(target.hasVolatileStatus(VolatileStatusType.THROAT_CHOPPED)).isTrue();
        assertThat(target.hasMovedThisTurn()).isTrue();
        assertThat(target.isLastMoveFailed()).isTrue();
        assertThat(target.getLastMoveUsed()).isNotEqualTo(soundMove);
    }

    @Test
    void testStruggleWhenThroatChoppedAndEncoredIntoSoundMove() {
        target.addVolatileStatus(VolatileStatusType.THROAT_CHOPPED, 2);
        target.addVolatileStatus(VolatileStatusType.ENCORED, 5, soundMove);

        new Turn(target, soundMove).takeTurn(battle);
        //Is this right? Should Struggle get set as the last move used?
        assertThat(target.getLastMoveUsed()).isNull();
    }

    @Test
    void testStruggleWhenThroatChoppedAndChoiceLockedIntoSoundMove() {
        target.addVolatileStatus(VolatileStatusType.THROAT_CHOPPED, 2);
        target.addVolatileStatus(VolatileStatusType.CHOICE_LOCKED, -1, soundMove);

        new Turn(target, soundMove).takeTurn(battle);
        //Is this right? Should Struggle get set as the last move used?
        assertThat(target.getLastMoveUsed()).isNull();
    }

    private static class MockSoundMove extends Move implements SoundEffect {

        static final int DAMAGE = 20;

        protected MockSoundMove() {
            super("MockSoundMove", 0, 100, Type.NORMAL, Move.Category.SPECIAL, Range.NORMAL, 8, false, 100);
        }

        @Override
        protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
            return DAMAGE;
        }

        @Override
        protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
            target.setStatus(Status.PARALYZE);
        }
    }
}
