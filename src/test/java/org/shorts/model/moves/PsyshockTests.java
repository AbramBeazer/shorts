package org.shorts.model.moves;

import org.junit.jupiter.api.Test;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class PsyshockTests {

    @Test
    void testUsesSpecialAttackAndPhysicalDefense() {
        Pokemon attacker = getDummyPokemon();
        attacker.setAttack(100);
        attacker.setSpecialAttack(33);
        Pokemon defender = getDummyPokemon();
        defender.setDefense(11);
        defender.setSpecialDefense(100);
        Psyshock move = new Psyshock();
        assertThat(move.getAttackingStat(attacker, defender)).isEqualTo(attacker.calculateSpecialAttack());
        assertThat(move.getDefendingStat(attacker, defender)).isEqualTo(defender.calculateDefense());
    }
}