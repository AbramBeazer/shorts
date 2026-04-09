package org.shorts.model.moves.thawing;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.model.moves.Ember;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;
import static org.shorts.model.status.StatusType.*;

class ThawingMoveTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        target.setStatus(Status.createFreeze());
        battle = new DummyBattle(user, target);
        Main.RANDOM = ZERO_RANDOM;
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
    }

    @Test
    void testThawsFrozenTarget() {
        Move move = new Scald();
        move.executeWrapper(user, List.of(target), battle);
        assertThat(target.getStatus().getType()).isNotEqualTo(FREEZE);
    }

    @Test
    void testThawsFrozenUser() {
        Move move = new SteamEruption();
        user.setStatus(Status.createFreeze());

        new Turn(user, move, 0).takeTurn(battle);
        assertThat(user.getStatus().getType()).isNotEqualTo(FREEZE);
    }

    @Test
    void testRegularDamagingFireMoveThawsTargetButNotUser() {
        Move move = new Ember();
        target.setStatus(Status.createFreeze());
        move.executeWrapper(user, List.of(target), battle);
        assertThat(target.getStatus().getType()).isNotEqualTo(FREEZE);

        user.setStatus(Status.createFreeze());
        new Turn(user, move, 0).takeTurn(battle);
        assertThat(user.getStatus().getType()).isEqualTo(FREEZE);
    }
}
