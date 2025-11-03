package org.shorts.model.moves;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.*;
import static org.shorts.model.types.Type.FLYING;
import static org.shorts.model.types.Type.WATER;

class FreezeDryTests {

    private Battle battle;
    private Pokemon user;
    private Pokemon target;
    private Move move;

    @BeforeEach
    void setup() {
        Main.RANDOM = ZERO_RANDOM;
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        move = new FreezeDry();
    }

    @Test
    void testGetMultiplierOnWaterType() {
        target.setTypes(Set.of(WATER));
        assertThat(move.getBaseTypeMultiplier(target.getTypes())).isEqualTo(Type.SUPER_EFFECTIVE);
    }

    @Test
    void testGetMultiplierQuadEffectiveOnWaterType() {
        target.setTypes(Set.of(WATER, FLYING));
        assertThat(move.getBaseTypeMultiplier(target.getTypes())).isEqualTo(Type.QUAD_EFFECTIVE);
    }

    @Test
    void testSecondaryFreezingEffect() {
        assertThat(target.getStatus()).isEqualTo(Status.NONE);
        move.executeOnTarget(user, target, battle);
        assertThat(target.getStatus()).isEqualTo(Status.FREEZE);
    }
}
