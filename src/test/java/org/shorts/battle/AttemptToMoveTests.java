package org.shorts.battle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.model.moves.Tackle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class AttemptToMoveTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testFrozenSetsMoveFailedToTrue() {
        user.setStatus(Status.FREEZE);
        new Turn(user, new Tackle(), 0).takeTurn(battle);
        assertThat(user.hasMovedThisTurn()).isFalse();
        assertThat(user.isLastMoveFailed()).isTrue();
        assertThat(target.isAtFullHP()).isTrue();
    }

    @Test
    void testParalysisSetsMoveFailedToTrue() {
        user.setStatus(Status.PARALYZE);
        new Turn(user, new Tackle(), 0).takeTurn(battle);
        assertThat(user.hasMovedThisTurn()).isFalse();
        assertThat(user.isLastMoveFailed()).isTrue();
        assertThat(target.isAtFullHP()).isTrue();
    }

    @Test
    void testConfusionSetsMoveFailedToTrue() {
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.CONFUSED, 3));
        new Turn(user, new Tackle(), 0).takeTurn(battle);
        assertThat(user.hasMovedThisTurn()).isFalse();
        assertThat(user.isLastMoveFailed()).isTrue();
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.isAtFullHP()).isFalse();
    }

    @Test
    void testInfatuationSetsMoveFailedToTrue() {
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.INFATUATED, 3));
        new Turn(user, new Tackle(), 0).takeTurn(battle);
        assertThat(user.hasMovedThisTurn()).isFalse();
        assertThat(user.isLastMoveFailed()).isTrue();
        assertThat(target.isAtFullHP()).isTrue();
    }
}
