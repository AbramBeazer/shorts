package org.shorts.model.moves.recoil;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.status.Status.FREEZE;

class FlareBlitzTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private FlareBlitz move;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        target.setStatus(Status.FREEZE);
        battle = new DummyBattle(user, target);
        Main.RANDOM = ZERO_RANDOM;
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        move = new FlareBlitz();
    }

    @Test
    void testThawsFrozenTarget() {
        move.execute(user, List.of(target), battle);
        assertThat(target.getStatus()).isNotEqualTo(FREEZE);
    }

    @Test
    void testThawsFrozenUser() {
        user.setStatus(Status.FREEZE);
        move.execute(user, List.of(target), battle);
        assertThat(user.getStatus()).isNotEqualTo(FREEZE);
    }
}
