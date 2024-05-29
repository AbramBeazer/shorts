package org.shorts.model.moves;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.SingleBattle;
import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Gyarados;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Squirtle;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.TestRandom.TEST_RANDOM;
import static org.shorts.model.abilities.Intimidate.INTIMIDATE;
import static org.shorts.model.abilities.PinchTypeBoostAbility.OVERGROW;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;
import static org.shorts.model.types.Type.FLYING;
import static org.shorts.model.types.Type.WATER;

class FreezeDryTests {

    private Battle battle;
    private Pokemon squirtle = new Squirtle(TORRENT);
    private Pokemon bulbasaur = new Bulbasaur(OVERGROW);
    private Pokemon gyarados = new Gyarados(INTIMIDATE);

    @BeforeEach
    void setup() {
        Main.RANDOM = TEST_RANDOM;
        battle = new SingleBattle(new Trainer("Red", List.of(squirtle)), new Trainer("Green", List.of(bulbasaur)));
    }

    @Test
    void testGetMultiplierOnWaterType() {
        Pokemon defender = squirtle;
        assertThat(defender.getTypes()).isEqualTo(Set.of(WATER));
        assertThat(new FreezeDry().getBaseTypeMultiplier(defender.getTypes())).isEqualTo(Type.SUPER_EFFECTIVE);
    }

    @Test
    void testGetMultiplierQuadEffectiveOnWaterType() {
        Pokemon defender = gyarados;
        assertThat(defender.getTypes()).isEqualTo(Set.of(WATER, FLYING));
        assertThat(new FreezeDry().getBaseTypeMultiplier(defender.getTypes())).isEqualTo(Type.QUAD_EFFECTIVE);
    }

    @Test
    void testApplySecondaryEffect() {
        Pokemon attacker = squirtle;
        Pokemon defender = bulbasaur;
        assertThat(defender.getStatus()).isNotEqualTo(Status.FREEZE);
        new FreezeDry().applySecondaryEffect(attacker, defender, battle);
        assertThat(defender.getStatus()).isEqualTo(Status.FREEZE);
    }

    @Test
    void testTrySecondaryEffect() {
        Pokemon attacker = squirtle;
        Pokemon defender = bulbasaur;
        assertThat(defender.getStatus()).isNotEqualTo(Status.FREEZE);
        assertThat(StatusType.FREEZE.isStatusPossible(defender, battle)).isTrue();
        new FreezeDry().trySecondaryEffect(attacker, defender, battle);
        assertThat(defender.getStatus()).isEqualTo(Status.FREEZE);
    }
}
