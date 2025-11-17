package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.StoneAxe;
import org.shorts.model.moves.Tackle;
import org.shorts.model.moves.multihit.CometPunch;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class SharpnessTests {

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
        final double withSlicingMove = Sharpness.SHARPNESS.getMovePowerMultipliers(
            user,
            target,
            battle,
            new StoneAxe());
        final double noSlicing = Sharpness.SHARPNESS.getMovePowerMultipliers(user, target, battle, new Tackle());
        assertThat(withSlicingMove).isEqualTo(noSlicing * Sharpness.SHARPNESS_BOOST);
    }
}
