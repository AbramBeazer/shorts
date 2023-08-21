package org.shorts.model.moves;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.TestRandom;
import org.shorts.model.Status;
import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Squirtle;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;

class FreezeDryTests {

    private FreezeDry freezeDry;

    @BeforeAll
    static void setRandom() {
        Main.RANDOM = new TestRandom();
    }

    @BeforeEach
    void setup() {
        freezeDry = new FreezeDry();
    }

    @Test
    void testGetMultiplierOnWaterType() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.WATER);
        assertThat(freezeDry.getMultiplier(attackerTypes, defenderTypes)).isEqualTo(Type.SUPER_EFFECTIVE);
    }

    @Test
    void testGetMultiplierQuadEffectiveOnWaterType() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.WATER, Type.GROUND);
        assertThat(freezeDry.getMultiplier(attackerTypes, defenderTypes)).isEqualTo(Type.QUAD_EFFECTIVE);
    }

    @Test
    void testSecondaryEffect() {
        Pokemon attacker = new Squirtle();
        Pokemon defender = new Bulbasaur();
        assertThat(defender.getStatus()).isNotEqualTo(Status.FREEZE);
        freezeDry.secondaryEffect(attacker, defender);
        assertThat(defender.getStatus()).isEqualTo(Status.FREEZE);
    }
}
