package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.multihit.MultiHitMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.PokemonTestUtils;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.*;

class StellarTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() {
        user = PokemonTestUtils.getDummyPokemon();
        target = PokemonTestUtils.getDummyPokemon();
        battle = new DummyBattle(user, target);
        Main.DAMAGE_RANDOM = MAX_RANDOM;
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
        final Move multiHitMove = new MultiHitMove(
            "test two hits",
            60,
            100,
            Type.NORMAL,
            Move.Category.PHYSICAL,
            Range.NORMAL,
            8,
            true,
            0,
            2,
            2) {

        };

        final int noTera = multiHitMove.calculateDamage(user, target, battle);
        final Type.StellarType stellar = new Type.StellarType();
        user.setTeraType(stellar);
        user.terastallize();
        final int withTera = multiHitMove.calculateDamage(user, target, battle);

        assertThat(withTera).isGreaterThanOrEqualTo((int) (noTera * Type.TERA_STAB
            / Type.STAB)); //this division is necessary because noTera still got regular STAB.

        stellar.getPreviouslyBoosted().clear();
        multiHitMove.doHit(user, target, battle);
        final int damage = target.getHpDiff();
        assertThat(damage).isGreaterThan(withTera + noTera);
        assertThat(damage).isEqualTo(withTera * 2);
    }
}
