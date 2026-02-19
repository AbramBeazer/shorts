package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class TemperFlareTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private TemperFlare move;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        move = new TemperFlare();
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testPowerIsNormalIfLastMoveSucceeded() {
        new Tackle().executeOnTarget(user, target, battle);
        assertThat(user.isLastMoveFailed()).isFalse();
        assertThat(move.getPowerMultipliers(user, target, battle)).isEqualTo(1);
    }

    @Test
    void testPowerDoublesIfLastMoveFailed() {
        user.setLastMoveFailed(true);
        assertThat(move.getPowerMultipliers(user, target, battle)).isEqualTo(TemperFlare.MULTIPLIER);
    }
}
