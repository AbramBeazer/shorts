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
import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Gyarados;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Squirtle;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;

class FreezeDryTests {

    private Battle battle;

    @BeforeAll
    static void setRandom() {
        Main.RANDOM = new TestRandom();
    }

    @BeforeEach
    void setup() {
        battle = new SingleBattle(new Trainer("a", List.of()), new Trainer("b", List.of()));
    }

    @Test
    void testGetMultiplierOnWaterType() {
        Pokemon defender = new Squirtle(TORRENT);
        assertThat(new FreezeDry().getTypeMultiplier(defender.getTypes())).isEqualTo(Type.SUPER_EFFECTIVE);
    }

    @Test
    void testGetMultiplierQuadEffectiveOnWaterType() {
        Pokemon defender = new Gyarados(TORRENT);
        assertThat(new FreezeDry().getTypeMultiplier(defender.getTypes())).isEqualTo(Type.QUAD_EFFECTIVE);
    }

    @Test
    void testSecondaryEffect() {
        Pokemon attacker = new Squirtle(TORRENT);
        Pokemon defender = new Bulbasaur(TORRENT);
        assertThat(defender.getStatus()).isNotEqualTo(Status.FREEZE);
        new FreezeDry().applySecondaryEffect(attacker, defender, battle);
        assertThat(defender.getStatus()).isEqualTo(Status.FREEZE);
    }
}
