package org.shorts.model.moves;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.MockRandomReturnMax;
import org.shorts.MockRandomReturnZero;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.abilities.Sharpness;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.Sharpness.SHARPNESS;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.types.Type.NORMAL;

class FuryCutterTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() {
        Set<Type> types = Set.of(NORMAL);
        user = getDummyPokemon();
        user.setTypes(types);
        target = getDummyPokemon();
        target.setTypes(types);
        battle = new DummySingleBattle();
        Main.RANDOM = new MockRandomReturnZero();
    }

    @Test
    void testPowerResetsIfMissed() {
        FuryCutter furyCutter = new FuryCutter();
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.determineTargetAndExecuteMove(user, target, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.determineTargetAndExecuteMove(user, target, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(2);

        Main.RANDOM = new MockRandomReturnMax();
        furyCutter.determineTargetAndExecuteMove(user, target, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);
    }

    @Test
    void testPowerDoublesWithEachHitButDoesNotExceedLimit() {
        FuryCutter furyCutter = new FuryCutter();
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.determineTargetAndExecuteMove(user, target, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.determineTargetAndExecuteMove(user, target, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(2);

        furyCutter.determineTargetAndExecuteMove(user, target, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(4);

        furyCutter.determineTargetAndExecuteMove(user, target, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(4);
    }

    @Test
    void testPowerResetsIfOtherMoveIsUsed() {
        FuryCutter furyCutter = new FuryCutter();
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.determineTargetAndExecuteMove(user, target, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.determineTargetAndExecuteMove(user, target, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(2);

        new Earthquake().determineTargetAndExecuteMove(user, target, battle);
        furyCutter.determineTargetAndExecuteMove(user, target, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);
    }

    @Test
    void testActivatesSharpness() {
        user.setAbility(SHARPNESS);

        FuryCutter furyCutter = new FuryCutter();
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.determineTargetAndExecuteMove(user, target, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.determineTargetAndExecuteMove(user, target, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(2);

        assertThat(furyCutter.calculateMovePower(user, target, battle)).isEqualTo(
            furyCutter.getPower() * furyCutter.getPowerMultipliers(user, target, battle) * Sharpness.SHARPNESS_BOOST);
    }
}
