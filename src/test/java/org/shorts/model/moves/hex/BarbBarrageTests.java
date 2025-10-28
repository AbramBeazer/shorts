package org.shorts.model.moves.hex;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class BarbBarrageTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private BarbBarrage barbBarrage;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        barbBarrage = new BarbBarrage();
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testDoublePowerToPoisonedTarget() {
        final double powerNoStatus = barbBarrage.getPower(user, target, battle);
        target.setStatus(Status.POISON);
        final double powerStatused = barbBarrage.getPower(user, target, battle);
        assertThat(powerStatused).isEqualTo(powerNoStatus * BarbBarrage.MULTIPLIER);
    }

    @Test
    void testDoublePowerToToxicTarget() {
        final double powerNoStatus = barbBarrage.getPower(user, target, battle);
        target.setStatus(Status.createToxic());
        final double powerStatused = barbBarrage.getPower(user, target, battle);
        assertThat(powerStatused).isEqualTo(powerNoStatus * BarbBarrage.MULTIPLIER);
    }

    @Test
    void testBurn() {
        assertThat(target.getStatus()).isEqualTo(Status.NONE);
        barbBarrage.execute(user, List.of(target), battle);
        assertThat(target.getStatus()).isEqualTo(Status.POISON);
    }
}
