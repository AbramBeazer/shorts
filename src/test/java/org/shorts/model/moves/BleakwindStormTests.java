package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Weather;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

public class BleakwindStormTests {
    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private BleakwindStorm move;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        move = new BleakwindStorm();
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testLowersTargetSpeed() {
        move.executeOnTarget(user, target, battle);

        assertThat(target.getStageSpeed()).isEqualTo(-1);
        assertThat(target.isAtFullHP()).isFalse();
    }

    @Test
    void testAlwaysHitsDuringRain() {
        Main.HIT_RANDOM = MAX_RANDOM; //Move will miss unless rain is up

        battle.setWeather(Weather.RAIN, 5);
        assertThat(move.rollToHit(user, target, battle)).isTrue();
        battle.setWeather(Weather.EXTREME_RAIN, 5);
        assertThat(move.rollToHit(user, target, battle)).isTrue();
    }

    @Test
    void testMissesSemiInvulnerableTargetsEvenInRain() { //TODO: Implement semi-invulnerability
        Main.HIT_RANDOM = MAX_RANDOM; //Move will miss unless rain is up

        battle.setWeather(Weather.RAIN, 5);
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.SEMI_INVULNERABLE, 1, new Dig()));

        assertThat(move.rollToHit(user, target, battle)).isTrue();
        move.executeOnTarget(user, target, battle);
        assertThat(target.isAtFullHP()).isTrue();
        assertThat(target.getStatus()).isEqualTo(Status.NONE);
    }
}
