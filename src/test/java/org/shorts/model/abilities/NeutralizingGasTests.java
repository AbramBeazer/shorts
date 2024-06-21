package org.shorts.model.abilities;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.Comatose.COMATOSE;
import static org.shorts.model.abilities.Intimidate.INTIMIDATE;
import static org.shorts.model.abilities.NeutralizingGas.NEUTRALIZING_GAS;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.status.VolatileStatusType.ABILITY_SUPPRESSED;

class NeutralizingGasTests {

    private Pokemon user;
    private Pokemon ally;
    private Pokemon target;
    private Pokemon otherTarget;
    private Battle battle;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        user.setAbility(NEUTRALIZING_GAS);
        ally = getDummyPokemon();
        ally.setAbility(TORRENT);
        target = getDummyPokemon();
        target.setAbility(COMATOSE);
        otherTarget = getDummyPokemon();
        otherTarget.setAbility(INTIMIDATE);
        battle = new Battle(
            new Trainer("Red", List.of(user, ally), 2),
            new Trainer("Green", List.of(target, otherTarget), 2),
            2);
    }

    @Test
    void testSuppressesAbilitiesThatCanBeSuppressed() {
        user.afterEntry(battle);
        assertThat(ally.hasVolatileStatus(ABILITY_SUPPRESSED)).isTrue();
        assertThat(target.hasVolatileStatus(ABILITY_SUPPRESSED)).isFalse();
        assertThat(otherTarget.hasVolatileStatus(ABILITY_SUPPRESSED)).isTrue();
    }

    @Test
    void testIntimidateDoesNotActivate() {
        assertThat(false).isTrue();
    }

    @Test
    void testIntimidateActivatesWhenNeutralizingGasLeaves() {
        assertThat(false).isTrue();
    }
}
