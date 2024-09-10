package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.StatusImmuneAbility.IMMUNITY;
import static org.shorts.model.abilities.ThickFat.THICK_FAT;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class ThickFatTests {

    private Pokemon snorlax;
    private Pokemon foe;
    private Battle battle;

    @BeforeEach
    void setup() {
        snorlax = getDummyPokemon();
        foe = getDummyPokemon();
        battle = new DummyBattle();
    }

    @Test
    void testHalvesAttackForOpposingIceMoves() {
        Move move = new IceFang();
        assertThat(move.getType()).isEqualTo(Type.ICE);

        snorlax.setAbility(IMMUNITY);
        double atk = move.getAttackingStat(foe, snorlax, battle);

        snorlax.setAbility(THICK_FAT);
        double reducedAtk = move.getAttackingStat(foe, snorlax, battle);

        assertThat(atk).isEqualTo(reducedAtk * 2);
    }

    @Test
    void testHalvesAttackForOpposingFireMoves() {
        Move move = new Ember();
        assertThat(move.getType()).isEqualTo(Type.FIRE);

        snorlax.setAbility(IMMUNITY);
        double spAtk = move.getAttackingStat(foe, snorlax, battle);

        snorlax.setAbility(THICK_FAT);
        double reducedSpAtk = move.getAttackingStat(foe, snorlax, battle);

        assertThat(spAtk).isEqualTo(reducedSpAtk * 2);
    }
}
