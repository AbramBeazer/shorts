package org.shorts.model.abilities;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.moves.Ember;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.XScissor;
import org.shorts.model.moves.priority.plusOne.GrassyGlide;
import org.shorts.model.moves.thawing.Scald;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.DummyAbility.DUMMY_ABILITY;

class PinchTypeBoostAbilityTests {

    private PinchTypeBoostAbility ability;
    private Pokemon attacker;
    private Pokemon defender;
    private Battle battle;

    @BeforeEach
    void setup() {
        attacker = new Pokemon(99, 99, DUMMY_ABILITY);
        defender = new Pokemon(99, 99, DUMMY_ABILITY);
        battle = new Battle(new Trainer("Red", List.of(attacker)), new Trainer("Green", List.of(defender)), 1);
    }

    void testActivatesOnlyUnder33PercentHP(Move move, PinchTypeBoostAbility ability) {
        attacker.setAbility(ability);
        assertThat(move.getType()).isEqualTo(ability.getType());
        assertThat(ability.getAttackMultipliers(attacker, defender, battle, move)).isEqualTo(1.0);
        attacker.setCurrentHP(attacker.getMaxHP() / 3);
        assertThat(ability.getAttackMultipliers(attacker, defender, battle, move)).isEqualTo(1.5);
    }

    @Test
    void testTorrent() {
        testActivatesOnlyUnder33PercentHP(new Scald(), PinchTypeBoostAbility.TORRENT);
    }

    @Test
    void testBlaze() {
        testActivatesOnlyUnder33PercentHP(new Ember(), PinchTypeBoostAbility.BLAZE);
    }

    @Test
    void testOvergrow() {
        testActivatesOnlyUnder33PercentHP(new GrassyGlide(), PinchTypeBoostAbility.OVERGROW);
    }

    @Test
    void testSwarm() {
        testActivatesOnlyUnder33PercentHP(new XScissor(), PinchTypeBoostAbility.SWARM);
    }
}
