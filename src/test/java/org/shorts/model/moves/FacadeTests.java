package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.PokemonTestUtils;
import org.shorts.model.status.Status;

import static org.assertj.core.api.Assertions.assertThat;

class FacadeTests {

    private Facade facade;
    private final Battle battle = new DummySingleBattle();

    @BeforeEach
    void setUp() {
        facade = new Facade();
    }

    @Test
    void testActivatesOnlyForBurnParalyzeAndPoison() {
        Pokemon user = PokemonTestUtils.getDummyPokemon();
        Pokemon target = PokemonTestUtils.getDummyPokemon();

        user.setStatus(Status.NONE);
        assertThat(facade.getPowerMultipliers(user, target, battle)).isEqualTo(1);
        user.setStatus(Status.FREEZE);
        assertThat(facade.getPowerMultipliers(user, target, battle)).isEqualTo(1);
        user.setStatus(Status.createSleep());
        assertThat(facade.getPowerMultipliers(user, target, battle)).isEqualTo(1);
        user.setStatus(Status.TOXIC_POISON);
        assertThat(facade.getPowerMultipliers(user, target, battle)).isEqualTo(Facade.MULTIPLIER);
        user.setStatus(Status.POISON);
        assertThat(facade.getPowerMultipliers(user, target, battle)).isEqualTo(Facade.MULTIPLIER);
        user.setStatus(Status.BURN);
        assertThat(facade.getPowerMultipliers(user, target, battle)).isEqualTo(Facade.MULTIPLIER);
        user.setStatus(Status.PARALYZE);
        assertThat(facade.getPowerMultipliers(user, target, battle)).isEqualTo(Facade.MULTIPLIER);
    }
}
