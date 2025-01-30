package org.shorts.model.abilities.elementabsorb;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.Earthquake;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.moves.SandAttack;
import org.shorts.model.moves.Soak;
import org.shorts.model.moves.Surf;
import org.shorts.model.moves.Thunder;
import org.shorts.model.moves.ThunderFang;
import org.shorts.model.moves.ThunderWave;
import org.shorts.model.moves.multihit.MultiHitMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.PokemonTestUtils;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.abilities.elementabsorb.ElementAbsorbingAbility.EARTH_EATER;
import static org.shorts.model.abilities.elementabsorb.ElementAbsorbingAbility.VOLT_ABSORB;
import static org.shorts.model.abilities.elementabsorb.ElementAbsorbingAbility.WATER_ABSORB;

class ElementAbsorbingAbilityTests {

    private Pokemon user;
    private Pokemon other;
    private Battle battle;

    @BeforeEach
    void setup() {
        user = PokemonTestUtils.getDummyPokemon();
        other = PokemonTestUtils.getDummyPokemon();
        battle = new DummyBattle(user, other);
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = ZERO_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testDoesNotActivateIfProtected() {
        user.setAbility(VOLT_ABSORB);
        final int halfHealth = user.getMaxHP() / 2;
        user.setCurrentHP(halfHealth);
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.PROTECTED, 1));
        new ThunderFang().execute(other, List.of(user), battle);
        assertThat(user.getCurrentHP()).isEqualTo(halfHealth);
    }

    @Test
    void testOnlyActivatedOnceByMultiHitMove() {
        user.setAbility(VOLT_ABSORB);
        final int halfHealth = user.getMaxHP() / 2;
        user.setCurrentHP(halfHealth);
        final Move move = new MultiHitMove(
            "Test",
            10,
            100,
            Type.ELECTRIC,
            Move.Category.SPECIAL,
            Range.NORMAL_SINGLE_ADJACENT_ANY,
            24,
            false,
            0,
            2,
            2) {

        };
        move.execute(other, List.of(user), battle);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() * 3 / 4);
    }

    @Test
    void testVoltAbsorbElectricAttack() {
        user.setAbility(VOLT_ABSORB);
        final Move move = new Thunder();
        user.takeDamage(10);
        move.execute(other, List.of(user), battle);
        assertThat(user.getStatus()).isEqualTo(Status.NONE);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP());
    }

    @Test
    void testVoltAbsorbElectricStatusMove() {
        user.setAbility(VOLT_ABSORB);
        final Move move = new ThunderWave();
        user.takeDamage(10);
        move.execute(other, List.of(user), battle);
        assertThat(user.getStatus()).isEqualTo(Status.NONE);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP());
    }

    @Test
    void testWaterAbsorbWaterAttack() {
        user.setAbility(WATER_ABSORB);
        final Move move = new Surf();
        user.takeDamage(10);
        move.execute(other, List.of(user), battle);
        assertThat(user.getStatus()).isEqualTo(Status.NONE);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP());
    }

    //TODO: Should water-type status moves like Soak trigger this ability? Thunder Wave will trigger Volt Absorb.
    @Test
    void testWaterAbsorbAbsorbsWaterStatusMove() {
        assertThat(user.getTypes()).doesNotContain(Type.WATER);
        user.setAbility(WATER_ABSORB);
        final Move move = new Soak();
        user.takeDamage(10);
        move.execute(other, List.of(user), battle);
        assertThat(user.getTypes()).doesNotContain(Type.WATER);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP());
    }

    @Test
    void testEarthEaterGroundAttack() {
        user.setAbility(EARTH_EATER);
        final Move move = new Earthquake();
        user.takeDamage(10);
        move.execute(other, List.of(user), battle);
        assertThat(user.getStatus()).isEqualTo(Status.NONE);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP());
    }

    @Test
    void testEarthEaterGroundStatusMove() {
        user.setAbility(EARTH_EATER);
        final Move move = new SandAttack();
        user.takeDamage(10);
        move.execute(other, List.of(user), battle);
        assertThat(user.getStageAccuracy()).isZero();
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP());
    }
}
