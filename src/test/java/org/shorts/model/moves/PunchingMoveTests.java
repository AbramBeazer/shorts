package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.abilities.IronFist;
import org.shorts.model.items.PunchingGlove;
import org.shorts.model.moves.multihit.CometPunch;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class PunchingMoveTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle();
    }

    @Test
    void testIronFistStacksWithPunchingGlove() {
        final Move move = new CometPunch();
        final double base = move.calculateMovePower(user, target, battle);

        user.setAbility(IronFist.IRON_FIST);
        user.setHeldItem(PunchingGlove.PUNCHING_GLOVE);
        final double withBonuses = move.calculateMovePower(user, target, battle);

        assertThat(withBonuses).isEqualTo(base * PunchingGlove.MULTIPLIER * IronFist.MULTIPLIER);
    }
}
