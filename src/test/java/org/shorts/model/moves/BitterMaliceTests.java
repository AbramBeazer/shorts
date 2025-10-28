package org.shorts.model.moves;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.abilities.Contrary;
import org.shorts.model.abilities.statpreserving.StatPreservingAbility;
import org.shorts.model.abilities.statpreserving.StatPreservingIgnorableAbility;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class BitterMaliceTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private BitterMalice bitterMalice;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        target.setTypes(Set.of(Type.FLYING));
        battle = new DummyBattle(user, target);
        bitterMalice = new BitterMalice();
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testAttackDrop() {
        assertThat(target.getStageAttack()).isZero();
        bitterMalice.executeOnTarget(user, target, battle);
        assertThat(target.getStageAttack()).isEqualTo(-1);
    }

    @Test
    void testAttackDropAgainstContrary() {
        target.setAbility(Contrary.CONTRARY);
        assertThat(target.getStageAttack()).isZero();
        bitterMalice.executeOnTarget(user, target, battle);
        assertThat(target.getStageAttack()).isOne();
    }

    @Test
    void testNoAttackDropIfTargetImmune() {
        target.setTypes(Set.of(Type.NORMAL));
        assertThat(target.getStageAttack()).isZero();
        bitterMalice.executeOnTarget(user, target, battle);
        assertThat(target.getStageAttack()).isZero();
    }

    @Test
    void testNoAttackDropAgainstHyperCutter() {
        target.setAbility(StatPreservingIgnorableAbility.HYPER_CUTTER);
        assertThat(target.getStageAttack()).isZero();
        bitterMalice.executeOnTarget(user, target, battle);
        assertThat(target.getStageAttack()).isZero();
    }

    @Test
    void testNoAttackDropAgainstFullMetalBody() {
        target.setAbility(StatPreservingAbility.FULL_METAL_BODY);
        assertThat(target.getStageAttack()).isZero();
        bitterMalice.executeOnTarget(user, target, battle);
        assertThat(target.getStageAttack()).isZero();
    }

    @Test
    void testAttackDropWorksAgainstOtherAbilities() {
        target.setAbility(StatPreservingIgnorableAbility.BIG_PECKS);
        assertThat(target.getStageAttack()).isZero();
        bitterMalice.executeOnTarget(user, target, battle);
        assertThat(target.getStageAttack()).isEqualTo(-1);
    }
}
