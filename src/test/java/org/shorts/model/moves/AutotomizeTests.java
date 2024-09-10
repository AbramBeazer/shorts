package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.abilities.Contrary;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.AutotomizedStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class AutotomizeTests {

    private Pokemon user;
    private Battle battle;
    private Move move;

    @BeforeEach
    void setUp() {
        battle = new DummyBattle();
        user = getDummyPokemon();
        move = new Autotomize();
    }

    @Test
    void testFailsIfSpeedIsMaxed() {
        user.setStageSpeed(6);
        move.executeOnTarget(user, user, battle);

        assertThat(user.hasVolatileStatus(VolatileStatusType.AUTOTOMIZED)).isFalse();
    }

    @Test
    void testFailsIfSpeedIsMinimumAndAbilityIsContrary() {
        user.setStageSpeed(-6);
        user.setAbility(Contrary.CONTRARY);
        move.executeOnTarget(user, user, battle);

        assertThat(user.hasVolatileStatus(VolatileStatusType.AUTOTOMIZED)).isFalse();
    }

    @Test
    void testRaisesSpeedAndAddsStatus() {
        user.setStageSpeed(2);
        move.executeOnTarget(user, user, battle);

        assertThat(user.hasVolatileStatus(VolatileStatusType.AUTOTOMIZED)).isTrue();
        assertThat(((AutotomizedStatus) user.getVolatileStatus(VolatileStatusType.AUTOTOMIZED)).getLevels()).isOne();
        assertThat(user.getStageSpeed()).isEqualTo(4);
    }

    @Test
    void testLowersSpeedAndAddsStatusWhenAbilityIsContrary() {
        user.setStageSpeed(2);
        user.setAbility(Contrary.CONTRARY);
        move.executeOnTarget(user, user, battle);

        assertThat(user.hasVolatileStatus(VolatileStatusType.AUTOTOMIZED)).isTrue();
        assertThat(((AutotomizedStatus) user.getVolatileStatus(VolatileStatusType.AUTOTOMIZED)).getLevels()).isOne();
        assertThat(user.getStageSpeed()).isZero();
    }

    @Test
    void testMultipleUsesAddsMultipleLevels() {
        user.setStageSpeed(3);

        move.executeOnTarget(user, user, battle);
        assertThat(user.hasVolatileStatus(VolatileStatusType.AUTOTOMIZED)).isTrue();
        assertThat(((AutotomizedStatus) user.getVolatileStatus(VolatileStatusType.AUTOTOMIZED)).getLevels()).isOne();
        assertThat(user.getStageSpeed()).isEqualTo(5);

        move.executeOnTarget(user, user, battle);
        assertThat(user.hasVolatileStatus(VolatileStatusType.AUTOTOMIZED)).isTrue();
        assertThat(((AutotomizedStatus) user.getVolatileStatus(VolatileStatusType.AUTOTOMIZED)).getLevels()).isEqualTo(2);
        assertThat(user.getStageSpeed()).isEqualTo(6);
    }

}
