package org.shorts.model.moves.hex;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class InfernalParadeTests {
    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private InfernalParade infernalParade;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        target.setTypes(Set.of(Type.FLYING));
        battle = new DummyBattle(user, target);
        infernalParade = new InfernalParade();
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testDoublePowerToStatusedTarget() {
        final double powerNoStatus = infernalParade.getPower(user, target, battle);
        target.setStatus(Status.PARALYZE);
        final double powerStatused = infernalParade.getPower(user, target, battle);
        assertThat(powerStatused).isEqualTo(powerNoStatus * InfernalParade.MULTIPLIER);
    }

    @Test
    void testBurn() {
        assertThat(target.getStatus()).isEqualTo(Status.NONE);
        infernalParade.execute(user, List.of(target), battle);
        assertThat(target.getStatus()).isEqualTo(Status.BURN);
    }
}
