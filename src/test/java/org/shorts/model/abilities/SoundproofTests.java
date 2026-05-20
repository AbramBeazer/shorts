package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.model.abilities.Soundproof.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class SoundproofTests {

    private Pokemon attacker;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() {
        attacker = getDummyPokemon();
        target = getDummyPokemon();
        target.setAbility(SOUNDPROOF);
        battle = new DummyBattle(attacker, target);
    }

    @Test
    void testSoundAttackDoesNoDamage() {
        assertThat(false).isTrue();
    }
}
