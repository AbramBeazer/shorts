package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.switchself.BatonPass;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class BatonPassTests {

    private Pokemon user;
    private Pokemon foe;
    private Battle battle;
    private Move bp;

    @BeforeEach
    void setup() {
        bp = new BatonPass();
        user = getDummyPokemon();
        foe = getDummyPokemon();
        battle = new DummyBattle();
    }

    @Test
    void testAbilitySuppressionNotBatonPassed() {
        user.addVolatileStatus(VolatileStatus.ABILITY_SUPPRESSED);
        bp.execute(user, List.of(user), battle);
        assertThat(false).isTrue();
    }
}
