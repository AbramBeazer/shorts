package org.shorts.model.moves;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.fixeddamage.DragonRage;
import org.shorts.model.moves.fixeddamage.SeismicToss;
import org.shorts.model.moves.multihit.IcicleSpear;
import org.shorts.model.moves.trapping.MeanLook;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.*;
import static org.shorts.model.types.Type.*;

class SubstituteMoveTests {

    private Pokemon attacker;
    private Pokemon subber;
    private Battle battle;
    private SubstituteMove substituteMove;
    private SeismicToss seismicToss;
    private DragonRage dragonRage;
    private int subHp;

    @BeforeEach
    void setUp() {
        attacker = getDummyPokemon();
        subber = getDummyPokemon();
        subber.setMaxHP(400);
        subber.setCurrentHP(400);
        subHp = subber.getMaxHP() / 4;
        battle = new DummyBattle(attacker, subber);
        substituteMove = new SubstituteMove();
        seismicToss = new SeismicToss();
        dragonRage = new DragonRage();
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testSubHasQuarterOfHP() {
        assertThat(subber.isAtFullHP()).isTrue();
        substituteMove.executeOnTarget(subber, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        SubstituteStatus sub = (SubstituteStatus) subber.getVolatileStatus(VolatileStatusType.SUBSTITUTE);
        assertThat(sub.getSubHP()).isEqualTo(subHp);
    }

    @Test
    void testSubTakesDamageWithoutBreaking() {
        assertThat(subber.isAtFullHP()).isTrue();
        substituteMove.executeOnTarget(subber, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        SubstituteStatus sub = (SubstituteStatus) subber.getVolatileStatus(VolatileStatusType.SUBSTITUTE);

        dragonRage.executeOnTarget(attacker, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        assertThat(sub.getSubHP()).isEqualTo(subHp - DragonRage.FIXED_DAMAGE);
        assertThat(subber.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)).isTrue();
    }

    @Test
    void testSubBreaksWhenSubHpReachesZero() {
        assertThat(subber.isAtFullHP()).isTrue();
        substituteMove.executeOnTarget(subber, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        SubstituteStatus sub = (SubstituteStatus) subber.getVolatileStatus(VolatileStatusType.SUBSTITUTE);
        assertThat(sub.getSubHP()).isEqualTo(attacker.getLevel());

        seismicToss.executeOnTarget(attacker, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        assertThat(sub.getSubHP()).isEqualTo(subHp - attacker.getLevel()).isZero();
        assertThat(subber.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)).isFalse();
    }

    @Test
    void testSubBreaksAfterTwoHits() {
        assertThat(subber.isAtFullHP()).isTrue();
        substituteMove.executeOnTarget(subber, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        SubstituteStatus sub = (SubstituteStatus) subber.getVolatileStatus(VolatileStatusType.SUBSTITUTE);
        assertThat(sub.getSubHP()).isEqualTo(attacker.getLevel());

        dragonRage.executeOnTarget(attacker, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        assertThat(sub.getSubHP()).isEqualTo(subHp - DragonRage.FIXED_DAMAGE);
        assertThat(subber.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)).isTrue();

        seismicToss.executeOnTarget(attacker, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        assertThat(sub.getSubHP()).isEqualTo(subHp - attacker.getLevel()).isZero();
        assertThat(subber.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)).isFalse();
    }

    @Test
    void testMultiHitMoveHitsTargetAfterSubBreaks() {
        subber.setMaxHP(12);
        subber.setCurrentHP(12);
        subHp = subber.getMaxHP() / 4;
        assertThat(subber.isAtFullHP()).isTrue();
        substituteMove.executeOnTarget(subber, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        SubstituteStatus sub = (SubstituteStatus) subber.getVolatileStatus(VolatileStatusType.SUBSTITUTE);

        subber.setTypes(Set.of(DRAGON, FLYING));
        new IcicleSpear().executeOnTarget(attacker, subber, battle);
        assertThat(sub.getSubHP()).isZero();
        assertThat(subber.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)).isFalse();
        assertThat(subber.getCurrentHP()).isLessThan(subber.getMaxHP() - subHp);
    }

    @Test
    void testSubBlockStatus() {
        assertThat(subber.isAtFullHP()).isTrue();
        substituteMove.executeOnTarget(subber, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        SubstituteStatus sub = (SubstituteStatus) subber.getVolatileStatus(VolatileStatusType.SUBSTITUTE);

        new Spore().executeOnTarget(attacker, subber, battle);
        assertThat(subber.getStatus()).isEqualTo(Status.NONE);
    }

    @Test
    void testSubBlocksStatDrop() {
        assertThat(subber.getStageDefense()).isZero();
        assertThat(subber.isAtFullHP()).isTrue();
        substituteMove.executeOnTarget(subber, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        SubstituteStatus sub = (SubstituteStatus) subber.getVolatileStatus(VolatileStatusType.SUBSTITUTE);

        new Crunch().executeOnTarget(attacker, subber, battle);
        assertThat(subber.getStageDefense()).isZero();
    }

    @Test
    void testSubPreventsFlinching() {
        assertThat(subber.isAtFullHP()).isTrue();
        substituteMove.executeOnTarget(subber, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        SubstituteStatus sub = (SubstituteStatus) subber.getVolatileStatus(VolatileStatusType.SUBSTITUTE);

        new Bite().executeOnTarget(attacker, subber, battle);
        assertThat(subber.hasVolatileStatus(VolatileStatusType.FLINCH)).isFalse();
    }

    @Test
    void testSubBlocksCantEscapeStatus() {
        assertThat(subber.isAtFullHP()).isTrue();
        substituteMove.executeOnTarget(subber, subber, battle);
        assertThat(subber.getCurrentHP()).isEqualTo(subber.getMaxHP() - subHp);
        SubstituteStatus sub = (SubstituteStatus) subber.getVolatileStatus(VolatileStatusType.SUBSTITUTE);

        new MeanLook().executeOnTarget(attacker, subber, battle);
        assertThat(subber.hasVolatileStatus(VolatileStatusType.CANT_ESCAPE)).isFalse();
    }
}
