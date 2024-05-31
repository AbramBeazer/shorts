package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class StoredPowerTests {

    private Pokemon user;
    private Pokemon target;
    private final Battle battle = new DummySingleBattle();
    private StoredPower storedPower;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        storedPower = new StoredPower();
    }

    @Test
    void testMaxBoosts() {
        user.setStageAttack(6);
        user.setStageDefense(6);
        user.setStageSpecialAttack(6);
        user.setStageSpecialDefense(6);
        user.setStageSpeed(6);
        user.setStageEvasion(6);
        user.setStageAccuracy(6);

        assertThat(storedPower.getPowerMultipliers(user, target, battle)).isEqualTo((StoredPower.MAX_BOOSTS + 1));
    }

    @Test
    void testNoBoosts() {
        assertThat(storedPower.getPowerMultipliers(user, target, battle)).isEqualTo(1);
    }

    @Test
    void testNegativeStatStagesAreNotConsidered() {
        user.setStageAttack(5);
        user.setStageDefense(-2);

        assertThat(storedPower.getPowerMultipliers(user, target, battle)).isEqualTo(user.getStageAttack() + 1);
    }
}
