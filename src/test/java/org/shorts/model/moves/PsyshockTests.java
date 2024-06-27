package org.shorts.model.moves;

import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class PsyshockTests {

    @Test
    void testUsesSpecialAttackAndPhysicalDefense() {
        Battle battle = new DummyBattle();
        Pokemon attacker = getDummyPokemon();
        attacker.setAttack(100);
        attacker.setSpecialAttack(33);
        Pokemon defender = getDummyPokemon();
        defender.setDefense(11);
        defender.setSpecialDefense(100);
        Psyshock move = new Psyshock();
        assertThat(move.getAttackingStat(attacker, defender, battle)).isEqualTo(attacker.calculateSpecialAttack());
        assertThat(move.getDefendingStat(attacker, defender, battle)).isEqualTo(defender.calculateDefense(battle));
    }
}
