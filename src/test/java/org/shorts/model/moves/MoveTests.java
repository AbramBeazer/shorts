package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.PokemonTestUtils;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;

class MoveTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() {
        user = PokemonTestUtils.getDummyPokemon();
        target = PokemonTestUtils.getDummyPokemon();
        battle = new DummyBattle(user, target);
    }

    @Test
    void testStellarSuperEffective() {
        final Type.StellarType stellar = new Type.StellarType();
        final Move stellarMove = new Move("test", 70, 100, stellar, Move.Category.SPECIAL, Range.NORMAL, 8, false, 0) {

        };

        target.setTera(true);
        target.setTeraType(Type.FIRE);
        assertThat(stellarMove.getTypeMultiplier(user, target, battle)).isEqualTo(Type.SUPER_EFFECTIVE);

        target.setTeraType(stellar);
        assertThat(stellarMove.getTypeMultiplier(user, target, battle)).isEqualTo(Type.SUPER_EFFECTIVE);

        target.setTera(false);
        assertThat(stellarMove.getTypeMultiplier(user, target, battle)).isEqualTo(Type.NEUTRAL);
    }

    @Test
    void testStellarBoostAppliesToEveryHitOfMultiHitMove() {
        assertThat(false).isTrue();
    }
}
