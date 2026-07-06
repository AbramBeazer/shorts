package org.shorts.model.abilities;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.battle.Weather;
import org.shorts.model.moves.DynamicPunch;
import org.shorts.model.moves.Growl;
import org.shorts.model.moves.IceFang;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.moves.SpringtideStorm;
import org.shorts.model.moves.StoneEdge;
import org.shorts.model.moves.Tackle;
import org.shorts.model.moves.ThunderFang;
import org.shorts.model.moves.ThunderWave;
import org.shorts.model.moves.Twister;
import org.shorts.model.moves.WillOWisp;
import org.shorts.model.moves.ZapCannon;
import org.shorts.model.moves.recoil.Struggle;
import org.shorts.model.moves.recoil.TakeDown;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class WonderGuardTests {

    private Pokemon attacker;
    private Pokemon guarder;
    private List<Pokemon> targets;
    private Battle battle;

    @BeforeEach
    void setup() {
        attacker = getDummyPokemon();
        guarder = getDummyPokemon();
        guarder.setAbility(WonderGuard.WONDER_GUARD);
        targets = List.of(guarder);
        battle = new DummyBattle(attacker, guarder);
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
    }

    @Test
    void testImmuneToNonSuperEffective() {
        final Move move = new ZapCannon();
        assertThat(Type.getTypeMultiplier(move.getType(), guarder.getTypes())).isLessThan(Type.SUPER_EFFECTIVE);
        move.execute(attacker, targets, battle);
        assertThat(guarder.isAtFullHP()).isTrue();
        assertThat(guarder.getStatus()).isEqualTo(Status.NONE);
    }

    @Test
    void testSupereffectiveMovesHit() {
        final Move move = new DynamicPunch();
        assertThat(Type.getTypeMultiplier(move.getType(), guarder.getTypes())).isEqualTo(Type.SUPER_EFFECTIVE);
        move.execute(attacker, targets, battle);
        assertThat(guarder.isAtFullHP()).isFalse();
        assertThat(guarder.hasVolatileStatus(VolatileStatusType.CONFUSED)).isTrue();
    }

    @Test
    void testOneHitKOMoves() {
        //Fissure, Guillotine, Horn Drill, Sheer Cold
        assertThat(false).isTrue();
    }

    @Test
    void testCounteringMoves() {
        //Bide, Comeuppance, Counter, Metal Burst, Mirror Coat
        assertThat(false).isTrue();
    }

    @Test
    void testDirectDamageMoves() {
        assertThat(false).isTrue();
        //Dragon Rage, Endeavor, Final Gambit, Guardian of Alola, Nature's Madness, Night Shade, Psywave, Ruination, Seismic Toss, Sonic Boom, Super Fang
    }

    @Test
    void testRecoilDoesDamage() {
        new TakeDown().execute(guarder, List.of(attacker), battle);
        assertThat(guarder.isAtFullHP()).isFalse();
    }

    @Test
    void testStatusMovesWork() {
        new Growl().execute(attacker, targets, battle);
        assertThat(guarder.getStageAttack()).isEqualTo(-1);

        guarder.setTypes(Set.of(Type.GROUND));
        new ThunderWave().execute(attacker, targets, battle);
        assertThat(guarder.getStatus()).isEqualTo(Status.NONE);

        guarder.setTypes(Set.of(Type.NORMAL));
        new ThunderWave().execute(attacker, targets, battle);
        assertThat(guarder.getStatus()).isEqualTo(Status.PARALYZE);
        guarder.fullRestore();

        guarder.setTypes(Set.of(Type.FIRE));
        new WillOWisp().execute(attacker, targets, battle);
        assertThat(guarder.getStatus()).isEqualTo(Status.NONE);
    }

    @Test
    void testTypelessDamageIsBlocked() {
        new Move("Test Typeless", 10, 100, Type.TYPELESS, Move.Category.PHYSICAL, Range.NORMAL, 8, true, 0) {

        }.execute(attacker, targets, battle);

        assertThat(guarder.isAtFullHP()).isTrue();
    }

    @Test
    void testStruggleHits() {
        Struggle.STRUGGLE.execute(attacker, targets, battle);
        assertThat(guarder.isAtFullHP()).isFalse();
    }

    @Test
    void testConfusedSelfDamageApplies() {
        guarder.addVolatileStatus(new VolatileStatus(VolatileStatusType.CONFUSED, -1));
        new Turn(attacker, new Tackle(), 0).takeTurn(battle);
        assertThat(guarder.isAtFullHP()).isFalse();
        assertThat(attacker.isAtFullHP()).isTrue();
    }

    @Test
    void testWeatherDoesDamage() {
        assertThat(false).isTrue();
    }

    @Test
    void testPoisonDoesDamage() {
        assertThat(false).isTrue();
    }

    @Test
    void testBurnDoesDamage() {
        assertThat(false).isTrue();
    }

    @Test
    void testLeechSeedDoesDamage() {
        assertThat(false).isTrue();
    }

    @Test
    void testHazardsDealDamage() {
        assertThat(false).isTrue();
    }

    @Test
    void testFlyingTypeHasNoWeaknessesDuringStrongWinds() {
        battle.setWeather(Weather.EXTREME_WIND, -1);
        guarder.setTypes(Set.of(Type.DRAGON, Type.FLYING));

        new StoneEdge().execute(attacker, targets, battle);
        assertThat(guarder.isAtFullHP()).isTrue();

        new ThunderFang().execute(attacker, targets, battle);
        assertThat(guarder.isAtFullHP()).isTrue();

        new IceFang().execute(attacker, targets, battle);
        assertThat(guarder.isAtFullHP()).isFalse();
        assertThat(guarder.getStatus()).isEqualTo(Status.FREEZE);
        guarder.fullRestore();

        new SpringtideStorm().execute(attacker, targets, battle);
        assertThat(guarder.isAtFullHP()).isFalse();
        assertThat(guarder.getStageAttack()).isEqualTo(-1);
        guarder.fullRestore();

        new Twister().execute(attacker, targets, battle);
        assertThat(guarder.isAtFullHP()).isFalse();
        assertThat(guarder.hasVolatileStatus(VolatileStatusType.FLINCH)).isTrue();
        guarder.fullRestore();
    }

    @Test
    void testTarringMakesTargetWeakToFire() {
        assertThat(false).isTrue();
    }

    @Test
    void testTraceCopiesWonderGuard() {
        assertThat(false).isTrue();
    }

    @Test
    void testTransformCopiesWonderGuard() {
        assertThat(false).isTrue();
    }

    @Test
    void testImposterCopiesWonderGuard() {
        assertThat(false).isTrue();
    }

    @Test
    void testRolePlayDoesNotCopyWonderGuard() {
        assertThat(false).isTrue();
    }

    @Test
    void testSkillSwapDoesNotCopyWonderGuard() {
        assertThat(false).isTrue();
    }

    @Test
    void testPowerOfAlchemyDoesNotCopyWonderGuard() {
        assertThat(false).isTrue();
    }

    @Test
    void testReceiverDoesNotCopyWonderGuard() {
        assertThat(false).isTrue();
    }

    @Test
    void testWanderingSpiritDoesNotCopyWonderGuard() {
        assertThat(false).isTrue();
    }
}
