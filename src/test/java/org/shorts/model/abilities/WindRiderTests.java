package org.shorts.model.abilities;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Weather;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.PetalBlizzard;
import org.shorts.model.moves.Tailwind;
import org.shorts.model.moves.switchtarget.Whirlwind;
import org.shorts.model.moves.weather.Sandstorm;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

public class WindRiderTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private Move move;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        target.setAbility(WindRider.WIND_RIDER);
        battle = new DummyBattle(user, target);
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testImmuneToWindMoves() {
        move = new PetalBlizzard();
        move.execute(user, List.of(target), battle);

        assertThat(target.isAtFullHP()).isTrue();
        assertThat(target.getStageAttack()).isOne();
    }

    @Test
    void testActivatedByWhirlwind() {
        move = new Whirlwind();
        move.execute(user, List.of(target), battle);
        //TODO: Does the switch get forced?
        assertThat(false).isTrue();
    }

    //TODO: What happens if the target has this ability, uses Ingrain, and then gets hit by Whirlwind?

    @Test
    void testActivatedByTailwind() {
        double regularSpeed = target.calculateSpeed(battle);
        move = new Tailwind();
        move.execute(target, List.of(target), battle);

        assertThat(target.getStageAttack()).isOne();
        assertThat(target.calculateSpeed(battle)).isEqualTo(2 * regularSpeed);
    }

    @Test
    void testNotActivatedBySandstorm() {
        move = new Sandstorm();
        move.execute(user, List.of(user, target), battle);

        assertThat(battle.getWeather()).isEqualTo(Weather.SAND);
        assertThat(target.getStageAttack()).isZero();
    }
}
