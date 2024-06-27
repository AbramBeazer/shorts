package org.shorts.model.moves;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Weather;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.abilities.AttackDoublingAbility.HUGE_POWER;
import static org.shorts.model.items.ChoiceBand.CHOICE_BAND;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class BodyPressTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private BodyPress bp;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        user.setTypes(Set.of(Type.GROUND));
        target = getDummyPokemon();
        target.setTypes(Set.of(Type.GROUND));
        battle = new DummyBattle(user, target);
        bp = new BodyPress();
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
    }

    @Test
    void testUsesDefenseAndDefenseStage() {
        user.setAttack(1);
        user.setDefense(100);
        target.setDefense(50);

        final Crunch crunch = new Crunch();
        assertThat(crunch.getPower(user, target, battle)).isEqualTo(bp.getPower(user, target, battle));
        final int slamDamage = crunch.calculateDamage(user, target, battle);
        final int pressDamage = bp.calculateDamage(user, target, battle);

        assertThat(pressDamage).isGreaterThan(slamDamage * 20);
    }

    @Test
    void testBurnStillHalvesDamage() {
        final int healthyDamage = bp.calculateDamage(user, target, battle);
        user.setStatus(Status.BURN);
        final int burnedDamage = bp.calculateDamage(user, target, battle);

        assertThat(healthyDamage).isEqualTo(burnedDamage * 2);
    }

    @Test
    void testHugePowerStillDoublesDamage() {
        final int baseDamage = bp.calculateDamage(user, target, battle);
        user.setAbility(HUGE_POWER);
        final int damageWithHugePower = bp.calculateDamage(user, target, battle);

        assertThat(damageWithHugePower).isGreaterThan(baseDamage);
    }

    @Test
    void testChoiceBandStillIncreasesDamage() {
        final int baseDamage = bp.calculateDamage(user, target, battle);
        user.setHeldItem(CHOICE_BAND);
        final int damageWithBand = bp.calculateDamage(user, target, battle);

        assertThat(damageWithBand).isGreaterThan(baseDamage);
    }

    @Test
    void testSnowBoostsDamageForIceType() {
        final int baseDamage = bp.calculateDamage(user, target, battle);
        user.setTypes(Set.of(Type.ICE));
        battle.setWeather(Weather.SNOW, 5);

        final int snowDamage = bp.calculateDamage(user, target, battle);

        assertThat(snowDamage).isGreaterThan(baseDamage);
    }

    @Test
    void testEvioliteDoesNotBoostDamage() {
        assertThat(false).isTrue();
    }

    @Test
    void testFurCoatDoesNotBoostDamage() {
        assertThat(false).isTrue();
    }

    @Test
    void testSlowStartDecreasesDamage() {
        assertThat(false).isTrue();
    }

    @Test
    void testWorksWithProtosynthesisBoost() {
        assertThat(false).isTrue();
    }

    @Test
    void testWorksWithQuarkDriveBoost() {
        assertThat(false).isTrue();
    }

}
