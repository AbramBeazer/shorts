package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class StoredPowerTests {

    private Pokemon user;
    private Pokemon target;
    private final Battle battle = new DummyBattle();
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
        final double expectedPower = (StoredPower.MAX_BOOSTS + 1) * StoredPower.BASE_POWER;

        assertThat(storedPower.getPower(user, target, battle)).isEqualTo(expectedPower);
    }

    @Test
    void testNoBoosts() {
        assertThat(storedPower.getPower(user, target, battle)).isEqualTo(StoredPower.BASE_POWER);
    }

    @Test
    void testNegativeStatStagesAreNotConsidered() {
        user.setStageAttack(5);
        user.setStageDefense(-2);

        final double expectedPower = (user.getStageAttack() + 1) * StoredPower.BASE_POWER;
        assertThat(storedPower.getPower(user, target, battle)).isEqualTo(expectedPower);
    }
}
