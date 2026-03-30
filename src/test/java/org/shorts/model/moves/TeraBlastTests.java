package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.PokemonTestUtils;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class TeraBlastTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private Move move;

    @BeforeEach
    void setup() {
        user = PokemonTestUtils.getDummyPokemon();
        target = PokemonTestUtils.getDummyPokemon();
        battle = new DummyBattle(user, target);
        move = new TeraBlast();
    }

    @Test
    void testStellarSuperEffective() {
        assertThat(false).isTrue();
    }

    @Test
    void testUsesTeraType() {
        user.setTeraType(Type.FIRE);
        user.terastallize();
        assertThat(move.getType()).isEqualTo(Type.FIRE);
    }

    @Test
    void testGetsSTABForTeraType() {
        user.setTeraType(Type.FIRE);
        user.terastallize();
        assertThat(Type.getSTABMultiplier(move.getType(), user)).isEqualTo(Type.STAB);
    }

    @Test
    void testGetsTeraSTABForOriginalType() {
        user.setTeraType(Type.NORMAL);
        user.terastallize();
        assertThat(Type.getSTABMultiplier(move.getType(), user)).isEqualTo(Type.TERA_STAB);
    }

    @Test
    void testLowerStatsForStellar() {
        assertThat(false).isTrue();
    }

    @Test
    void test100PowerForStellar() {
        assertThat(false).isTrue();
    }

    @Test
    void testUsesHigherAttackStatAfterStages() {
        assertThat(false).isTrue();
    }

    @Test
    void testGalvanizeDoesNotChangeTypeWhenTera() {
        assertThat(false).isTrue();
    }

    @Test
    void testGaleWingsDoesNotBoostPriorityOfFlyingTeraBlast() {
        assertThat(false).isTrue();
    }

    @Test
    void testAffectedByGalvanizeWhenNotTera() {
        assertThat(false).isTrue();
    }
}
