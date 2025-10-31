package org.shorts.model.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Tackle;
import org.shorts.model.moves.multihit.CometPunch;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class PunchingGloveTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
    }

    @Test
    void testPowerBoosted() {
        final double withPunchingMove = PunchingGlove.PUNCHING_GLOVE.getMovePowerMultipliers(
            user,
            target,
            battle,
            new CometPunch());
        final double noPunching = PunchingGlove.PUNCHING_GLOVE.getMovePowerMultipliers(
            user,
            target,
            battle,
            new Tackle());
        assertThat(withPunchingMove).isEqualTo(noPunching * PunchingGlove.MULTIPLIER);
    }

    @Test
    void testNegatesContactForPunchingMoves() {
        final CometPunch cometPunch = new CometPunch();
        final Tackle tackle = new Tackle();

        assertThat(cometPunch.isContact(user)).isTrue();
        assertThat(tackle.isContact(user)).isTrue();

        user.setHeldItem(PunchingGlove.PUNCHING_GLOVE);
        assertThat(cometPunch.isContact(user)).isFalse();
        assertThat(tackle.isContact(user)).isTrue();
    }
}
