package org.shorts.battle;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.shorts.model.moves.floating.DoomDesire;
import org.shorts.model.moves.floating.FutureSight;
import org.shorts.model.moves.floating.Wish;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class FloatingEffectsTests {

    @Test
    void testDoomDesire() {
        final Pokemon user = getDummyPokemon();
        final Pokemon target = getDummyPokemon();
        final Battle battle = new DummyBattle(user, target);
        final DoomDesire move = new DoomDesire();

        move.execute(user, List.of(target), battle);
        for (int i = 0; i < move.getDuration(); i++) {
            battle.handleFloatingEffects();
        }

        assertThat(target.isAtFullHP()).isFalse();
    }

    @Test
    void testFutureSight() {
        final Pokemon user = getDummyPokemon();
        final Pokemon target = getDummyPokemon();
        final Battle battle = new DummyBattle(user, target);
        final FutureSight move = new FutureSight();

        move.execute(user, List.of(target), battle);
        for (int i = 0; i < move.getDuration(); i++) {
            battle.handleFloatingEffects();
        }

        assertThat(target.isAtFullHP()).isFalse();
    }

    @Test
    void testWish() {
        final Pokemon user = getDummyPokemon();
        user.setMaxHP(100);
        final Pokemon target = getDummyPokemon();
        target.setMaxHP(70);
        target.setCurrentHP(1);
        final Battle battle = new DummyBattle(user, target);
        final Wish move = new Wish();

        move.execute(user, List.of(target), battle);
        for (int i = 0; i < move.getDuration(); i++) {
            battle.handleFloatingEffects();
        }
        assertThat(target.getCurrentHP()).isEqualTo((user.getMaxHP() / 2) + 1);
    }
}
