package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.model.abilities.Infiltrator.*;
import static org.shorts.model.abilities.Soundproof.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class HowlTests {

    private Pokemon user;
    private Pokemon ally;
    private Battle battle;
    private Howl howl;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        ally = getDummyPokemon();
        battle = new DummyBattle(List.of(user, ally), List.of(getDummyPokemon(), getDummyPokemon()), 2);
        howl = new Howl();
    }

    @Test
    void testFailsWhenAttackIsMaxed() {
        user.setStageAttack(6);
        assertThat(howl.affectsTarget(user, user)).isFalse();
        howl.execute(user, battle.getPlayerOne().getActivePokemon(), battle);
        assertThat(user.getStageAttack()).isEqualTo(6);
    }

    @Test
    void testAlwaysAffectsSelfWhenRaiseIsPossible() {
        user.addVolatileStatus(new SubstituteStatus(100));
        user.setAbility(SOUNDPROOF);
        assertThat(user.hasVolatileStatus(VolatileStatusType.ABILITY_IGNORED)).isEqualTo(user.hasVolatileStatus(
            VolatileStatusType.ABILITY_SUPPRESSED)).isFalse();
        assertThat(user.getStageAttack()).isZero();

        assertThat(howl.affectsTarget(user, user)).isTrue();
        howl.execute(user, battle.getPlayerOne().getActivePokemon(), battle);
        assertThat(user.getStageAttack()).isOne();
    }

    @Test
    void testFailsWhenAllyHasSoundproof() {
        ally.setAbility(SOUNDPROOF);
        assertThat(howl.affectsTarget(user, ally)).isFalse();
        howl.execute(user, battle.getPlayerOne().getActivePokemon(), battle);
        assertThat(user.getStageAttack()).isOne();
        assertThat(ally.getStageAttack()).isZero();
    }

    @Test
    void testFailsWhenAllyHasSubUnlessUserHasInfiltrator() {
        ally.addVolatileStatus(new SubstituteStatus(100));
        assertThat(howl.affectsTarget(user, ally)).isFalse();
        howl.execute(user, battle.getPlayerOne().getActivePokemon(), battle);
        assertThat(user.getStageAttack()).isOne();
        assertThat(ally.getStageAttack()).isZero();

        user.setAbility(INFILTRATOR);
        assertThat(howl.affectsTarget(user, ally)).isTrue();
        howl.execute(user, battle.getPlayerOne().getActivePokemon(), battle);
        assertThat(user.getStageAttack()).isEqualTo(2);
        assertThat(ally.getStageAttack()).isOne();
    }
}
