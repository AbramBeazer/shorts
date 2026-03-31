package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.MockRandomReturnMax;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.PokemonTestUtils;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;

class TeraStarstormTests {

    private Pokemon user;
    private Pokemon target1;
    private Pokemon target2;
    private Battle battle;
    private Move move;

    @BeforeEach
    void setup() {
        user = PokemonTestUtils.getDummyPokemon();
        user.setTeraType(new Type.StellarType());
        move = new TeraStarstorm();
        user.setMoves(List.of(move));
        target1 = PokemonTestUtils.getDummyPokemon();
        target1.setTeraType(Type.NORMAL);
        target2 = PokemonTestUtils.getDummyPokemon();
        target2.setTeraType(Type.NORMAL);
        battle = new DummyBattle(List.of(user, PokemonTestUtils.getDummyPokemon()), List.of(target1, target2), 2);
        Main.DAMAGE_RANDOM = MockRandomReturnMax.MAX_RANDOM;
    }

    @Test
    void testStellarSuperEffective() {
        user.terastallize();
        target1.setTera(false);
        target2.setTera(true);

        assertThat(move.getTypeMultiplier(user, target1, battle)).isEqualTo(Type.NEUTRAL);
        assertThat(move.getTypeMultiplier(user, target2, battle)).isEqualTo(Type.SUPER_EFFECTIVE);
    }

    @Test
    void testHitsOneOpponentIfNoTera() {
        new Turn(user, move, 0).takeTurn(battle);

        assertThat(target1.isAtFullHP()).isFalse();
        assertThat(target2.isAtFullHP()).isTrue();
    }

    @Test
    void testHitsBothOpponentsIfTera() {
        user.terastallize();
        new Turn(user, move, 0).takeTurn(battle);

        assertThat(target1.isAtFullHP()).isFalse();
        assertThat(target2.isAtFullHP()).isFalse();
    }

    @Test
    void testUsesHigherAttackStatAfterStagesWhenTera() {
        final int higherAttack = 300;
        final int lowerSpAtk = 299;
        user.setAttack(higherAttack);
        user.setSpecialAttack(lowerSpAtk);

        new Turn(user, move, 0).takeTurn(battle);
        assertThat(move.getCategory()).isEqualTo(Move.Category.SPECIAL);
        assertThat(move.getAttackingStat(user, target1, battle)).isEqualTo(lowerSpAtk);

        target1.fullRestore();
        user.terastallize();
        new Turn(user, move, 0).takeTurn(battle);
        assertThat(move.getCategory()).isEqualTo(Move.Category.PHYSICAL);
        assertThat(move.getAttackingStat(user, target1, battle)).isEqualTo(higherAttack);

        target1.fullRestore();
        user.setStageSpecialAttack(1);
        new Turn(user, move, 0).takeTurn(battle);
        assertThat(move.getCategory()).isEqualTo(Move.Category.SPECIAL);
        assertThat(move.getAttackingStat(user, target1, battle)).isEqualTo(lowerSpAtk * 1.5);

        target1.fullRestore();
        user.setStageAttack(3);
        user.setTera(false);
        new Turn(user, move, 0).takeTurn(battle);
        assertThat(move.getCategory()).isEqualTo(Move.Category.SPECIAL);
        assertThat(move.getAttackingStat(user, target1, battle)).isEqualTo(lowerSpAtk * 1.5);
    }

    @Test
    void testGalvanizeDoesNotChangeTypeWhenTera() {
        assertThat(false).isTrue();
    }
}
