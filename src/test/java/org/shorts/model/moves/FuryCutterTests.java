package org.shorts.model.moves;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.abilities.Sharpness;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.abilities.Sharpness.SHARPNESS;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.types.Type.NORMAL;

class FuryCutterTests {

    private Pokemon user;
    private Pokemon target;
    private List<Pokemon> allTargets;
    private Battle battle;

    @BeforeEach
    void setup() {
        Set<Type> types = Set.of(NORMAL);
        user = getDummyPokemon();
        user.setTypes(types);
        target = getDummyPokemon();
        target.setTypes(types);
        allTargets = List.of(target);
        battle = new DummyBattle();
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
    }

    @Test
    void testPowerResetsIfMissed() {
        FuryCutter furyCutter = new FuryCutter();
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.execute(user, allTargets, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.execute(user, allTargets, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(2);

        Main.HIT_RANDOM = MAX_RANDOM;
        furyCutter.execute(user, allTargets, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);
    }

    @Test
    void testPowerDoublesWithEachHitButDoesNotExceedLimit() {
        FuryCutter furyCutter = new FuryCutter();
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.execute(user, allTargets, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.execute(user, allTargets, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(2);

        furyCutter.execute(user, allTargets, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(4);

        furyCutter.execute(user, allTargets, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(4);
    }

    @Test
    void testPowerResetsIfOtherMoveIsUsed() {
        FuryCutter furyCutter = new FuryCutter();
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.execute(user, allTargets, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.execute(user, allTargets, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(2);

        new Earthquake().execute(user, allTargets, battle);
        furyCutter.execute(user, allTargets, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);
    }

    @Test
    void testActivatesSharpness() {
        user.setAbility(SHARPNESS);

        FuryCutter furyCutter = new FuryCutter();
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.execute(user, allTargets, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(1);

        furyCutter.execute(user, allTargets, battle);
        assertThat(furyCutter.getPowerMultipliers(user, target, battle)).isEqualTo(2);

        assertThat(furyCutter.calculateMovePower(user, target, battle)).isEqualTo(
            furyCutter.getPower(user, target, battle) * furyCutter.getPowerMultipliers(user, target, battle)
                * Sharpness.SHARPNESS_BOOST);
    }
}
