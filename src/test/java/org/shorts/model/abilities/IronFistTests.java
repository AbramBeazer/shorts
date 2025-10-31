package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.Tackle;
import org.shorts.model.moves.multihit.CometPunch;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class IronFistTests {

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
        final double withPunchingMove = IronFist.IRON_FIST.getMovePowerMultipliers(
            user,
            target,
            battle,
            new CometPunch());
        final double noPunching = IronFist.IRON_FIST.getMovePowerMultipliers(user, target, battle, new Tackle());
        assertThat(withPunchingMove).isEqualTo(noPunching * IronFist.MULTIPLIER);
    }
}
