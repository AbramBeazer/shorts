package org.shorts.model.moves;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.model.abilities.Prankster;
import org.shorts.model.moves.fixeddamage.SonicBoom;
import org.shorts.model.moves.switchtarget.DragonTail;
import org.shorts.model.moves.switchtarget.SwitchTargetMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.Main.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class PursuitTests {

    private Pokemon user;
    private Pokemon ally;
    private Pokemon target;
    private Pokemon otherTarget;
    private Battle battle;
    private Move pursuit;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon("user");
        ally = getDummyPokemon("ally");
        target = getDummyPokemon("target");
        otherTarget = getDummyPokemon("otherTarget");
        List<Pokemon> playerOneTeam = new ArrayList<>();
        playerOneTeam.add(user);
        playerOneTeam.add(ally);
        List<Pokemon> playerTwoTeam = new ArrayList<>();
        playerTwoTeam.add(target);
        playerTwoTeam.add(otherTarget);
        battle = new DummyBattle(playerOneTeam, playerTwoTeam, 2);
        pursuit = new Pursuit();
        HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testPowerDoublesWhenUserIsSwitching() {
        assertThat(false).isTrue();
    }

    @Test
    void testAffectedByKingsRock() {
        assertThat(false).isTrue();
    }

    @Test
    void testBypassesRedirectionFromFollowMe() {
        assertThat(false).isTrue();
    }

    @Test
    void testActivatesAndSkipsAccuracyCheckIfTargetUsesUTurnFirst() {
        //        final Move pivot = new UTurn();
        //        pivot.execute(target, user, battle);
        HIT_RANDOM = MAX_RANDOM;
        assertThat(false).isTrue();
    }

    @Test
    void testActivatesAndSkipsAccuracyCheckIfTargetUsesVoltSwitchFirst() {
        HIT_RANDOM = MAX_RANDOM;
        assertThat(false).isTrue();
    }

    @Test
    void testActivatesAndSkipsAccuracyCheckIfTargetUsesPartingShotFirst() {
        HIT_RANDOM = MAX_RANDOM;
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotWorkOnBatonPass() {
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotWorkOnTeleport() {
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotWorkOnAllySwitching() {
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotWorkAfterSwitchTargetMove() {
        battle.getPlayerTwo().getTeam().add(getDummyPokemon("bench 1"));
        final Move switchTargetMove = new SwitchTargetMove(
            "Fake move",
            1,
            -1,
            Type.GRASS,
            Move.Category.SPECIAL,
            Range.NORMAL,
            8,
            false,
            0) {

            @Override
            public int getPriority(Pokemon attacker, Battle battle) {
                return 1;
            }
        };
        final List<Turn> turns = new ArrayList<>();
        turns.add(new Turn(ally, switchTargetMove, 0));
        turns.add(new Turn(user, pursuit, 0));

        battle.takeTurns(turns);

        final Pokemon bench1 = battle.getPlayerTwo().getActivePokemon().get(0);
        assertThat(bench1.isAtFullHP()).isFalse();
    }

    @Test
    void testDoesNotWorkAfterEjectButton() {
        battle.getPlayerTwo().getTeam().add(getDummyPokemon("bench 1"));
        //        target.setHeldItem(EjectButton.EJECT_BUTTON); TODO: Implement
        final List<Turn> turns = new ArrayList<>();
        turns.add(new Turn(ally, new SonicBoom(), 0));
        turns.add(new Turn(user, pursuit, 0));

        battle.takeTurns(turns);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP() - SonicBoom.FIXED_DAMAGE);
        final Pokemon bench1 = battle.getPlayerTwo().getActivePokemon().get(0);
        assertThat(bench1).isNotSameAs(target);
        assertThat(bench1.isAtFullHP()).isFalse();
    }

    @Test
    void testHitsFirstEnemyToSwitchEvenIfNotTargeted() {
        battle.getPlayerTwo().getTeam().add(getDummyPokemon("bench 1"));
        battle.getPlayerTwo().getTeam().add(getDummyPokemon("bench 2"));
        target.setSpeed(1); // ensure that target switches after otherTarget
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.CENTER_OF_ATTENTION, 3));

        final List<Turn> turns = new ArrayList<>();
        turns.add(new Turn(otherTarget, null, 2));
        turns.add(new Turn(target, null, 3));
        turns.add(new Turn(user, pursuit, 0)); //aiming at target
        turns.add(new Turn(ally, pursuit, 0));

        battle.takeTurns(turns);

        assertThat(target.isAtFullHP()).isTrue();
        assertThat(otherTarget.isAtFullHP()).isFalse();
        final Pokemon bench1 = battle.getPlayerTwo().getActivePokemon().get(0);
        final Pokemon bench2 = battle.getPlayerTwo().getActivePokemon().get(1);
        assertThat(bench1.isAtFullHP()).isTrue();
        assertThat(bench2.isAtFullHP()).isTrue();
    }

}
