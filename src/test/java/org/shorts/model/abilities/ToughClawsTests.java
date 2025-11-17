package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.Tackle;
import org.shorts.model.moves.priority.plusOne.IceShard;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class ToughClawsTests {

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
        final double withContact = ToughClaws.TOUGH_CLAWS.getMovePowerMultipliers(
            user,
            target,
            battle,
            new Tackle());
        final double nonContact = ToughClaws.TOUGH_CLAWS.getMovePowerMultipliers(user, target, battle, new IceShard());
        assertThat(withContact).isEqualTo(nonContact * ToughClaws.MULTIPLIER);
    }
}
