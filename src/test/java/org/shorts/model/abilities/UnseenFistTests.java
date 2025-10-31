package org.shorts.model.abilities;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.items.PunchingGlove;
import org.shorts.model.moves.StoneEdge;
import org.shorts.model.moves.Tackle;
import org.shorts.model.moves.multihit.CometPunch;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class UnseenFistTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        user.setAbility(UnseenFist.UNSEEN_FIST);
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.PROTECTED, 1));
    }

    @Test
    void testGetsThroughProtectWithContactMove() {
        assertThat(target.isAtFullHP()).isTrue();

        new Tackle().execute(user, List.of(target), battle);

        assertThat(target.isAtFullHP()).isFalse();
    }

    @Test
    void testTargetIsProtectedForNonContactMove() {
        assertThat(target.isAtFullHP()).isTrue();

        new StoneEdge().execute(user, List.of(target), battle);

        assertThat(target.isAtFullHP()).isTrue();
    }

    @Test
    void testTargetIsProtectedForPunchingMoveWithPunchingGlove() {
        assertThat(target.isAtFullHP()).isTrue();

        user.setHeldItem(PunchingGlove.PUNCHING_GLOVE);
        new CometPunch().execute(user, List.of(target), battle);

        assertThat(target.isAtFullHP()).isTrue();
    }
}
