package org.shorts.battle;

import org.junit.jupiter.api.Test;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class BattleTests {

    @Test
    void testPrintFieldSingleBattle() {
        final Pokemon user = getDummyPokemon();
        final Pokemon target = getDummyPokemon();
        final Battle battle = new DummySingleBattle(user, target);

        battle.printField(battle.getPlayerOne());

        battle.printField(battle.getPlayerTwo());
    }
}
