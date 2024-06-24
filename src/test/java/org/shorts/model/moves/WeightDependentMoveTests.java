package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class WeightDependentMoveTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() {
        this.user = getDummyPokemon();
        this.target = getDummyPokemon();
        battle = new DummySingleBattle(user, target);
    }

    @Test
    void testPowerAtEachLevel() {
        assertThat(false).isTrue();
    }
}
