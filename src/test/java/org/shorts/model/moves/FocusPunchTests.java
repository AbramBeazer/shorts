package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class FocusPunchTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private FocusPunch focusPunch;
    private final VolatileStatus status = new VolatileStatus(VolatileStatusType.FOCUS_PUNCH, 0);

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        focusPunch = new FocusPunch();
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testLosesFocusIfHitWithDamagingMove() {
        user.addVolatileStatus(status);
        new Tackle().executeOnTarget(target, user, battle);
        focusPunch.execute(user, List.of(target), battle);
        assertThat(user.isAtFullHP()).isFalse();
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.hasVolatileStatus(VolatileStatusType.FOCUS_PUNCH)).isFalse();
        assertThat(user.isLastMoveFailed()).isTrue();
    }

    @Test
    void testKeepsFocusIfHitWithNonDamagingMove() {
        user.addVolatileStatus(status);
        new Growl().executeOnTarget(target, user, battle);
        focusPunch.execute(user, List.of(target), battle);
        assertThat(user.isAtFullHP()).isTrue();
        assertThat(target.isAtFullHP()).isFalse();
        assertThat(user.hasVolatileStatus(VolatileStatusType.FOCUS_PUNCH)).isFalse();
        assertThat(user.isLastMoveFailed()).isFalse();
    }

    //TODO: Find some way to test a whole battle turn being executed from top to bottom -- maybe that's better for an integration test
    @Test
    void testSucceedsOnAttackerThatGoesLater() {
        target.setSpeed(1);
        //Have both user and target use Focus Punch. Target is slower, so User will attack first and break Target's concentration.
        assertThat(target.isAtFullHP()).isFalse();
        assertThat(target.isLastMoveFailed()).isTrue();
        assertThat(user.isAtFullHP()).isTrue();
        assertThat(user.isLastMoveFailed()).isFalse();
    }
}
