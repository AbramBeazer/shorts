package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.multihit.RockBlast;
import org.shorts.model.moves.priority.plusOne.VacuumWave;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class BeakBlastTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private BeakBlast beakBlast;
    private final VolatileStatus status = new VolatileStatus(VolatileStatusType.BEAK_BLAST, 0);

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        user.setMaxHP(1000);
        user.setCurrentHP(1000);
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        beakBlast = new BeakBlast();
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testBurnsAttackerIfContactMoveHits() {
        user.addVolatileStatus(status);
        new Tackle().executeOnTarget(target, user, battle);
        assertThat(target.getStatus()).isEqualTo(Status.BURN);
        beakBlast.execute(user, List.of(target), battle);
        assertThat(user.isAtFullHP()).isFalse();
        assertThat(target.isAtFullHP()).isFalse();
        assertThat(user.hasVolatileStatus(VolatileStatusType.BEAK_BLAST)).isFalse();
    }

    @Test
    void testDoesNotBurnIfMoveIsNotContact() {
        user.addVolatileStatus(status);
        new Growl().executeOnTarget(target, user, battle);
        new VacuumWave().executeOnTarget(target, user, battle);
        new RockBlast().executeOnTarget(target, user, battle);
        assertThat(target.getStatus()).isNotEqualTo(Status.BURN);
        beakBlast.execute(user, List.of(target), battle);
        assertThat(user.isAtFullHP()).isFalse();
        assertThat(target.isAtFullHP()).isFalse();
        assertThat(user.hasVolatileStatus(VolatileStatusType.BEAK_BLAST)).isFalse();
    }

    //TODO: Find some way to test a whole battle turn being executed from top to bottom -- maybe that's better for an integration test
    @Test
    void testDoesNotBurnEnemyThatGoesLater() {
        target.setSpeed(1);
        assertThat(target.getStatus()).isNotEqualTo(Status.BURN);
        assertThat(target.isAtFullHP()).isFalse();
        assertThat(user.isAtFullHP()).isFalse();
    }
}
