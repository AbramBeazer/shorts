package org.shorts.model.moves;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.MockRandomReturnZero;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.SubstituteStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.status.Status.NONE;
import static org.shorts.model.status.Status.PARALYZE;
import static org.shorts.model.types.Type.ELECTRIC;
import static org.shorts.model.types.Type.GROUND;

public class ThunderWaveTests {

    private ThunderWave tWave;
    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() {
        tWave = new ThunderWave();
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummySingleBattle();
        Main.RANDOM = new MockRandomReturnZero();
    }

    @Test
    void testParalyzesOpponent() {
        assertThat(target.getStatus()).isEqualTo(NONE);
        tWave.executeOnTarget(user, target, battle);
        assertThat(target.getStatus()).isEqualTo(PARALYZE);
    }

    @Test
    void testFailsOnElectricTargets() {
        target.setTypes(Set.of(ELECTRIC));
        assertThat(target.getStatus()).isEqualTo(NONE);
        tWave.executeOnTarget(user, target, battle);
        assertThat(target.getStatus()).isEqualTo(NONE);
    }

    @Test
    void testFailsOnGroundTargets() {
        target.setTypes(Set.of(GROUND));
        assertThat(target.getStatus()).isEqualTo(NONE);
        tWave.executeOnTarget(user, target, battle);
        assertThat(target.getStatus()).isEqualTo(NONE);
    }

    @Test
    void testFailsOnSubstitute() {
        target.addVolatileStatus(new SubstituteStatus(1));
        assertThat(target.getStatus()).isEqualTo(NONE);
        tWave.executeOnTarget(user, target, battle);
        assertThat(target.getStatus()).isEqualTo(NONE);
    }

}
