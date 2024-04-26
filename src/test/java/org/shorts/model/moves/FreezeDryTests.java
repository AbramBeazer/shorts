package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.TestRandom;
import org.shorts.battle.Battle;
import org.shorts.battle.SingleBattle;
import org.shorts.battle.Trainer;
import org.shorts.model.Status;
import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Gyarados;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Squirtle;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;

class FreezeDryTests {

    private FreezeDry freezeDry;
    private Battle battle;

    @BeforeAll
    static void setRandom() {
        Main.RANDOM = new TestRandom();
    }

    @BeforeEach
    void setup() {
        freezeDry = new FreezeDry();
        battle = new SingleBattle(new Trainer("a", List.of()), new Trainer("b", List.of()));
    }

    @Test
    void testGetMultiplierOnWaterType() throws Exception {
        Pokemon attacker = new Bulbasaur();
        Pokemon defender = new Squirtle();
        assertThat(freezeDry.getMultiplier(attacker, defender, battle)).isEqualTo(Type.SUPER_EFFECTIVE);
    }

    @Test
    void testGetMultiplierQuadEffectiveOnWaterType() throws Exception {
        Pokemon attacker = new Bulbasaur();
        Pokemon defender = new Gyarados();
        assertThat(freezeDry.getMultiplier(attacker, defender, battle)).isEqualTo(Type.QUAD_EFFECTIVE);
    }

    @Test
    void testSecondaryEffect() {
        Pokemon attacker = new Squirtle();
        Pokemon defender = new Bulbasaur();
        assertThat(defender.getStatus()).isNotEqualTo(Status.FREEZE);
        freezeDry.applySecondaryEffect(attacker, defender, battle);
        assertThat(defender.getStatus()).isEqualTo(Status.FREEZE);
    }
}
