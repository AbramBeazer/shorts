package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.PokemonTestUtils;
import org.shorts.model.types.Type;

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
        user.setMoves(List.of(move));
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
    void testStellarLowersStatsAndHasHigherPower() {
        assertThat(move.getPower(user, target, battle)).isEqualTo(TeraBlast.BASE_POWER);
        assertThat(user.getStageAttack()).isZero();
        assertThat(user.getStageSpecialAttack()).isZero();

        final Type.StellarType stellar = new Type.StellarType();
        user.setTeraType(stellar);
        user.terastallize();
        assertThat(move.getPower(user, target, battle)).isEqualTo(TeraBlast.STELLAR_POWER);

        assertThat(move.getTypeMultiplier(user, target, battle)).isEqualTo(Type.NEUTRAL);
        target.setTera(true);
        assertThat(move.getTypeMultiplier(user, target, battle)).isEqualTo(Type.SUPER_EFFECTIVE);

        move.executeOnTarget(user, target, battle);
        assertThat(user.getStageAttack()).isEqualTo(-1);
        assertThat(user.getStageSpecialAttack()).isEqualTo(-1);
    }

    @Test
    void testUsesHigherAttackStatAfterStagesWhenTera() {
        final int higherAttack = 300;
        final int lowerSpAtk = 299;
        user.setAttack(higherAttack);
        user.setSpecialAttack(lowerSpAtk);

        new Turn(user, move, 0).takeTurn(battle);
        assertThat(move.getCategory()).isEqualTo(Move.Category.SPECIAL);
        assertThat(move.getAttackingStat(user, target, battle)).isEqualTo(lowerSpAtk);

        target.fullRestore();
        user.setTeraType(Type.NORMAL);
        user.terastallize();
        new Turn(user, move, 0).takeTurn(battle);
        assertThat(move.getCategory()).isEqualTo(Move.Category.PHYSICAL);
        assertThat(move.getAttackingStat(user, target, battle)).isEqualTo(higherAttack);

        target.fullRestore();
        user.setStageSpecialAttack(1);
        new Turn(user, move, 0).takeTurn(battle);
        assertThat(move.getCategory()).isEqualTo(Move.Category.SPECIAL);
        assertThat(move.getAttackingStat(user, target, battle)).isEqualTo(lowerSpAtk * 1.5);

        target.fullRestore();
        user.setStageAttack(3);
        user.setTera(false);
        new Turn(user, move, 0).takeTurn(battle);
        assertThat(move.getCategory()).isEqualTo(Move.Category.SPECIAL);
        assertThat(move.getAttackingStat(user, target, battle)).isEqualTo(lowerSpAtk * 1.5);
    }

    @Test
    void testGalvanizeDoesNotChangeTypeWhenTera() {
        user.setTeraType(Type.NORMAL);
        user.terastallize();
        //        user.setAbility(GALVANIZE);
        assertThat(false).isTrue();
    }

    @Test
    void testAffectedByGalvanizeWhenNotTera() {
        //        user.setAbility(GALVANIZE);
        assertThat(move.getType()).isEqualTo(Type.ELECTRIC);
        //TODO: This may not be how it actually works -- I may have to check to see if it doesn't affect Ground and does x2 to water and flying.
    }

    @Test
    void testGaleWingsDoesNotBoostPriorityOfFlyingTeraBlast() {
        assertThat(false).isTrue();
    }

    //TODO: Ogerpon, Terapagos, Forest's Curse, Adaptability,
    //TODO: Terastallized Pokémon cannot have their type changed from moves and Abilities such as Soak, Double Shock, Imposter, Protean, or Transform.
    // A Pokémon using Transform before Terastallizing will not copy its opponent's Tera Type and will instead keep its own.
}
