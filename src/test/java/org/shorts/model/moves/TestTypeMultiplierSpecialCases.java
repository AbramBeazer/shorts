package org.shorts.model.moves;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.battle.Weather;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.Scrappy.SCRAPPY;
import static org.shorts.model.items.IronBall.IRON_BALL;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.status.VolatileStatusType.TARRED;
import static org.shorts.model.types.Type.FLYING;
import static org.shorts.model.types.Type.GHOST;
import static org.shorts.model.types.Type.GRASS;
import static org.shorts.model.types.Type.IMMUNE;
import static org.shorts.model.types.Type.NEUTRAL;
import static org.shorts.model.types.Type.NORMAL;
import static org.shorts.model.types.Type.NOT_VERY_EFFECTIVE;
import static org.shorts.model.types.Type.OCTO_EFFECTIVE;
import static org.shorts.model.types.Type.STEEL;
import static org.shorts.model.types.Type.SUPER_EFFECTIVE;

class TestTypeMultiplierSpecialCases {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummySingleBattle();
    }

    @Test
    void testUngroundedFlyingTypeWithIronBall() {
        final Move move = new Thunder();
        target.setTypes(Set.of(FLYING));
        target.setHeldItem(IRON_BALL);
        assertThat(move.getTypeMultiplier(user, target, battle)).isEqualTo(Type.NEUTRAL);
    }

    @Test
    void testUngroundedDualFlyingTypeWithIronBall() {
        final Move move = new Earthquake();
        target.setTypes(Set.of(STEEL, FLYING));
        target.setHeldItem(IRON_BALL);
        assertThat(move.getTypeMultiplier(user, target, battle)).isEqualTo(Type.NEUTRAL);
    }

    @Test
    void testUngroundedFlyingTypeAffectedByThousandArrows() {
        final Move move = new Thunder();
        target.setTypes(Set.of(FLYING));

        assertThat(true).isEqualTo(false);
    }

    @Test
    void testUngroundedDualFlyingTypeAffectedByThousandArrows() {
        final Move move = new Earthquake();
        target.setTypes(Set.of(STEEL, FLYING));

        assertThat(true).isEqualTo(false);
    }

    @Test
    void testFreezeDryOnUngroundedFlyingTypeWithIronBall() {
        final Move move = new FreezeDry();
        target.setTypes(Set.of(FLYING));
        target.setHeldItem(IRON_BALL);
        assertThat(move.getTypeMultiplier(user, target, battle)).isEqualTo(NEUTRAL);
    }

    @Test
    void testFlyingPressOnUngroundedFlyingTypeWithIronBall() {
        final Move move = new FlyingPress();
        target.setTypes(Set.of(FLYING));
        target.setHeldItem(IRON_BALL);
        assertThat(move.getTypeMultiplier(user, target, battle)).isEqualTo(NEUTRAL);
    }

    @Test
    void testGroundedFlyingTypeWithoutIronBallOrThousandArrows() {
        final Move move = new Earthquake();
        target.setTypes(Set.of(FLYING));
        assertThat(move.getTypeMultiplier(user, target, battle)).isEqualTo(NEUTRAL);
    }

    @Test
    void testGroundedDualFlyingTypeWithoutIronBallOrThousandArrows() {
        final Move ground = new Earthquake();
        final Move electric = new Thunder();
        target.setTypes(Set.of(STEEL, FLYING));
        assertThat(ground.getTypeMultiplier(user, target, battle)).isEqualTo(SUPER_EFFECTIVE);
        assertThat(electric.getTypeMultiplier(user, target, battle)).isEqualTo(SUPER_EFFECTIVE);
    }

    @Test
    void testRingTarget() {
        assertThat(false).isEqualTo(true);
    }

    @Test
    void testIdentifiedStatus() {
        assertThat(false).isEqualTo(true);
    }

    @Test
    void testExtremeWind() {
        battle.setWeather(Weather.EXTREME_WIND, -1);
        final Move ground = new Earthquake();
        final Move normal = new Tackle();
        final Move fighting = new CloseCombat();
        final Move electric = new Thunder();
        target.setTypes(Set.of(FLYING));
        assertThat(ground.getTypeMultiplier(user, target, battle)).isEqualTo(NEUTRAL);
        assertThat(normal.getTypeMultiplier(user, target, battle)).isEqualTo(NEUTRAL);
        assertThat(fighting.getTypeMultiplier(user, target, battle)).isEqualTo(NEUTRAL);
        assertThat(electric.getTypeMultiplier(user, target, battle)).isEqualTo(NEUTRAL);
    }

    @Test
    void testGroundedDualFlyingTypeWithoutIronBallOrThousandArrowsInExtremeWind() {
        final Move ground = new Earthquake();
        final Move electric = new Thunder();
        target.setTypes(Set.of(STEEL, FLYING));
        battle.setWeather(Weather.EXTREME_WIND, -1);
        assertThat(ground.getTypeMultiplier(user, target, battle)).isEqualTo(SUPER_EFFECTIVE);
        assertThat(electric.getTypeMultiplier(user, target, battle)).isEqualTo(NEUTRAL);
    }

    @Test
    void testScrappy() {
        final Move normal = new Tackle();
        final Move fighting = new CloseCombat();
        target.setTypes(Set.of(GHOST));

        assertThat(user.getAbility()).isNotEqualTo(SCRAPPY);
        assertThat(normal.getTypeMultiplier(user, target, battle)).isEqualTo(IMMUNE);
        assertThat(fighting.getTypeMultiplier(user, target, battle)).isEqualTo(IMMUNE);

        user.setAbility(SCRAPPY);
        assertThat(normal.getTypeMultiplier(user, target, battle)).isEqualTo(NEUTRAL);
        assertThat(fighting.getTypeMultiplier(user, target, battle)).isEqualTo(NEUTRAL);

        target.setTypes(Set.of(GHOST, STEEL));
        assertThat(normal.getTypeMultiplier(user, target, battle)).isEqualTo(NOT_VERY_EFFECTIVE);
        assertThat(fighting.getTypeMultiplier(user, target, battle)).isEqualTo(SUPER_EFFECTIVE);
    }

    @Test
    void testScrappyWithUngroundedGhostFlyingMonWithIronBall() {
        final Move fighting = new CloseCombat();
        user.setAbility(SCRAPPY);
        target.setHeldItem(IRON_BALL);
        target.setTypes(Set.of(GHOST, FLYING));

        //In this weird iron ball scenario, the type multiplier will always be 1, even if the move isn't ground-type.
        assertThat(fighting.getTypeMultiplier(user, target, battle)).isEqualTo(NEUTRAL);
    }

    @Test
    void testFireAttackOnTarredPokemon() {
        final Move fire = new Ember();
        target.setTypes(Set.of(NORMAL));
        target.addVolatileStatus(new VolatileStatus(TARRED, -1));
        assertThat(fire.getTypeMultiplier(user, target, battle)).isEqualTo(
            2 * Type.getTypeMultiplier(fire.getType(), target.getTypes()));
    }

    @Test
    void testFireAttackOnTarredFlyingTypeWithIronBall() {
        final Move fire = new Ember();
        target.setTypes(Set.of(FLYING));
        target.setHeldItem(IRON_BALL);
        target.addVolatileStatus(new VolatileStatus(TARRED, -1));
        assertThat(fire.getTypeMultiplier(user, target, battle)).isEqualTo(SUPER_EFFECTIVE);
    }

    @Test
    void testStrongWindTarred() {
        final Move fire = new Ember();
        battle.setWeather(Weather.EXTREME_WIND, -1);
        target.addVolatileStatus(new VolatileStatus(TARRED, -1));
        target.setTypes(Set.of(FLYING));
        assertThat(fire.getTypeMultiplier(user, target, battle)).isEqualTo(SUPER_EFFECTIVE);
    }

    @Test
    void testTarredQuadWeakToFire() {
        final Move fire = new Ember();
        target.addVolatileStatus(new VolatileStatus(TARRED, -1));
        target.setTypes(Set.of(GRASS, STEEL));
        assertThat(fire.getTypeMultiplier(user, target, battle)).isEqualTo(OCTO_EFFECTIVE);
    }

}
