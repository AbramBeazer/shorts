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
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

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
        user = getDummyPokemon();
        ally = getDummyPokemon();
        target = getDummyPokemon();
        otherTarget = getDummyPokemon();
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
        user.setSpeed(1);
        ally.setAbility(Prankster.PRANKSTER);
        final Move dragonTail = new DragonTail();
        final Turn allyTurn = new Turn(ally, dragonTail, 0);
        final Turn userTurn = new Turn(user, pursuit, 0);

        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotWorkAfterEjectButton() {
        battle.getPlayerTwo().getTeam().add(getDummyPokemon());
        //        target.setHeldItem(EjectButton.EJECT_BUTTON); TODO: Implement
        final Turn allyTurn = new Turn(ally, new SonicBoom(), 0);
        final Turn userTurn = new Turn(user, pursuit, 0);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP() - SonicBoom.FIXED_DAMAGE);
        final Pokemon newTarget = battle.getPlayerTwo().getActivePokemon().get(0);
        assertThat(newTarget).isNotSameAs(target);
        assertThat(newTarget.isAtFullHP()).isFalse();
    }

    @Test
    void testHitsFirstEnemyToSwitchEvenIfNotTargeted() {
        battle.getPlayerTwo().getTeam().add(getDummyPokemon());
        battle.getPlayerTwo().getTeam().add(getDummyPokemon());
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.CENTER_OF_ATTENTION, 3));
        final Turn otherTargetTurn = new Turn(otherTarget, null, 2);
        final Turn targetTurn = new Turn(target, null, 3);
        final Turn pursuitTurn = new Turn(user, pursuit, 0); //aiming at target

        otherTargetTurn.takeTurn(battle);
        targetTurn.takeTurn(battle);
        pursuitTurn.takeTurn(battle);

        assertThat(target.isAtFullHP()).isTrue();
        assertThat(otherTarget.isAtFullHP()).isFalse();
    }

}
