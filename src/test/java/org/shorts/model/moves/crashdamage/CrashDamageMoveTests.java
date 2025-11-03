package org.shorts.model.moves.crashdamage;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.abilities.Scrappy;
import org.shorts.model.abilities.elementabsorb.ElementAbsorbingAbility;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;
import static org.shorts.model.types.Type.*;

class CrashDamageMoveTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private CrashDamageMove move;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        move = new JumpKick();
        Main.RANDOM = ZERO_RANDOM;
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
    }

    @Test
    void testCannotBeUsedInGravity() {
        battle.setGravityTurns(1);
        move.execute(user, List.of(target), battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.isAtFullHP()).isTrue();
    }

    @Test
    void testCrashesIfTargetIsImmune() {
        target.setTypes(Set.of(GHOST));
        move.executeOnTarget(user, target, battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() - (user.getMaxHP() / 2));
    }

    @Test
    void testFightingMoveHitsWithScrappy() {
        target.setTypes(Set.of(GHOST));
        user.setAbility(Scrappy.SCRAPPY);
        move.executeOnTarget(user, target, battle);
        assertThat(target.isAtFullHP()).isFalse();
        assertThat(user.isAtFullHP()).isTrue();
    }

    @Test
    void testCrashesIfMoveMisses() {
        Main.HIT_RANDOM = MAX_RANDOM;
        move.executeOnTarget(user, target, battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() - (user.getMaxHP() / 2));
    }

    @Test
    void testCrashesIfTargetIsProtected() {
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.PROTECTED, 1));
        move.executeOnTarget(user, target, battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() - (user.getMaxHP() / 2));
    }

    @Test
    void testAxeKickConfusesTarget() {
        move = new AxeKick();
        move.executeOnTarget(user, target, battle);
        assertThat(target.isAtFullHP()).isFalse();
        assertThat(user.isAtFullHP()).isTrue();
        assertThat(target.hasVolatileStatus(VolatileStatusType.CONFUSED)).isTrue();
    }

    @Test
    void testSupercellSlamHitsMinimizedTarget() {
        move = new SupercellSlam();
        Main.HIT_RANDOM = MAX_RANDOM;
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.MINIMIZED, 1));
        move.executeOnTarget(user, target, battle);
        assertThat(target.isAtFullHP()).isFalse();
        assertThat(user.isAtFullHP()).isTrue();
    }

    @Test
    void testSupercellSlamCrashesWhenMinimizedTargetIsProtected() {
        move = new SupercellSlam();
        Main.HIT_RANDOM = MAX_RANDOM;
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.MINIMIZED, 1));
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.PROTECTED, 1));
        move.executeOnTarget(user, target, battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() - (user.getMaxHP() / 2));
    }

    @Test
    void testSupercellSlamCrashesWhenMinimizedTargetIsImmune() {
        move = new SupercellSlam();
        Main.HIT_RANDOM = MAX_RANDOM;
        target.setTypes(Set.of(GROUND));
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.MINIMIZED, 1));
        move.executeOnTarget(user, target, battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() - (user.getMaxHP() / 2));
    }

    @Test
    void testSupercellSlamCrashesWhenMinimizedTargetHasAbsorbingAbility() {
        move = new SupercellSlam();
        Main.HIT_RANDOM = MAX_RANDOM;
        target.setAbility(ElementAbsorbingAbility.VOLT_ABSORB);
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.MINIMIZED, 1));
        move.executeOnTarget(user, target, battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() - (user.getMaxHP() / 2));
    }
}
