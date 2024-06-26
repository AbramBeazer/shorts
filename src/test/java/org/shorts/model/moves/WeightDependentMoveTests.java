package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.pokemon.PokedexEntry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.AutotomizedStatus;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.DummyAbility.DUMMY_ABILITY;
import static org.shorts.model.abilities.HeavyMetal.HEAVY_METAL;
import static org.shorts.model.abilities.LightMetal.LIGHT_METAL;
import static org.shorts.model.items.FloatStone.FLOAT_STONE;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemonFromEntry;

class WeightDependentMoveTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() {
        this.user = getDummyPokemon();
        battle = new DummySingleBattle();
    }

    private Pokemon buildMonWithWeight(double weight) {
        PokedexEntry.PokedexEntryBuilder builder = PokedexEntry.PokedexEntryBuilder.createNewInstance();
        builder.setSpeciesName("TEST MON");
        builder.setHeight(1);
        builder.setWeight(weight);
        builder.setBaseHP(300);
        builder.setBaseAtk(100);
        builder.setBaseDef(100);
        builder.setBaseSpAtk(100);
        builder.setBaseSpDef(100);
        builder.setBaseSpeed(100);
        builder.setHiddenAbility(DUMMY_ABILITY.getName());
        builder.setAbility1(DUMMY_ABILITY.getName());
        builder.setType1(Type.NORMAL);
        return getDummyPokemonFromEntry(builder.build());
    }

    @Test
    void testGrassKnotAtEachLevel() {
        GrassKnot move = new GrassKnot();

        assertThat(move.getPower(user, buildMonWithWeight(2), battle)).isEqualTo(20);
        assertThat(move.getPower(user, buildMonWithWeight(10), battle)).isEqualTo(40);
        assertThat(move.getPower(user, buildMonWithWeight(25), battle)).isEqualTo(60);
        assertThat(move.getPower(user, buildMonWithWeight(50), battle)).isEqualTo(80);
        assertThat(move.getPower(user, buildMonWithWeight(100), battle)).isEqualTo(100);
        assertThat(move.getPower(user, buildMonWithWeight(200), battle)).isEqualTo(120);
    }

    @Test
    void testLowKickAtEachLevel() {
        LowKick move = new LowKick();

        assertThat(move.getPower(user, buildMonWithWeight(2), battle)).isEqualTo(20);
        assertThat(move.getPower(user, buildMonWithWeight(10), battle)).isEqualTo(40);
        assertThat(move.getPower(user, buildMonWithWeight(25), battle)).isEqualTo(60);
        assertThat(move.getPower(user, buildMonWithWeight(50), battle)).isEqualTo(80);
        assertThat(move.getPower(user, buildMonWithWeight(100), battle)).isEqualTo(100);
        assertThat(move.getPower(user, buildMonWithWeight(200), battle)).isEqualTo(120);
    }

    @Test
    void testGrassKnotWithWeightAlteringAbilities() {
        GrassKnot move = new GrassKnot();

        Pokemon target = buildMonWithWeight(16);
        double basePower = move.getPower(user, target, battle);

        target.setAbility(LIGHT_METAL);
        assertThat(move.getPower(user, target, battle)).isLessThan(basePower);
        target.setAbility(HEAVY_METAL);
        assertThat(move.getPower(user, target, battle)).isGreaterThan(basePower);
    }

    @Test
    void testLowKickWithWeightAlteringAbilities() {
        LowKick move = new LowKick();

        Pokemon target = buildMonWithWeight(16);
        double basePower = move.getPower(user, target, battle);

        target.setAbility(LIGHT_METAL);
        assertThat(move.getPower(user, target, battle)).isLessThan(basePower);
        target.setAbility(HEAVY_METAL);
        assertThat(move.getPower(user, target, battle)).isGreaterThan(basePower);
    }

    @Test
    void testHeatCrashAtEachLevel() {
        final Move move = new HeatCrash();
        final double userWeight = 10000;
        user = buildMonWithWeight(userWeight);

        assertThat(move.getPower(user, buildMonWithWeight(userWeight + 500), battle)).isEqualTo(40);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight - 1), battle)).isEqualTo(40);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .5001), battle)).isEqualTo(40);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .5), battle)).isEqualTo(60);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .3335), battle)).isEqualTo(60);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .3333), battle)).isEqualTo(80);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .2501), battle)).isEqualTo(80);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .25), battle)).isEqualTo(100);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .2001), battle)).isEqualTo(100);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .2), battle)).isEqualTo(120);
    }

    @Test
    void testHeavySlamAtEachLevel() {
        final Move move = new HeavySlam();
        final double userWeight = 10000;
        user = buildMonWithWeight(userWeight);

        assertThat(move.getPower(user, buildMonWithWeight(userWeight + 500), battle)).isEqualTo(40);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight - 1), battle)).isEqualTo(40);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .5001), battle)).isEqualTo(40);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .5), battle)).isEqualTo(60);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .3335), battle)).isEqualTo(60);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .3333), battle)).isEqualTo(80);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .2501), battle)).isEqualTo(80);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .25), battle)).isEqualTo(100);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .2001), battle)).isEqualTo(100);
        assertThat(move.getPower(user, buildMonWithWeight(userWeight * .2), battle)).isEqualTo(120);
    }

    @Test
    void testHeatCrashWithFloatStoneAndLightMetalAndHeavyMetal() {
        final Move move = new HeatCrash();
        final double userWeight = 10000;
        user = buildMonWithWeight(userWeight);
        target = buildMonWithWeight(userWeight);

        final double basePower = move.getPower(user, target, battle);
        assertThat(basePower).isEqualTo(40);
        target.setHeldItem(FLOAT_STONE);
        assertThat(move.getPower(user, target, battle)).isEqualTo(60);
        target.setAbility(LIGHT_METAL);
        assertThat(move.getPower(user, target, battle)).isEqualTo(100);
        target.setAbility(HEAVY_METAL);
        assertThat(move.getPower(user, target, battle)).isEqualTo(40);
    }

    @Test
    void testHeavySlamWithFloatStoneAndLightMetalAndHeavyMetal() {
        final Move move = new HeavySlam();
        final double userWeight = 10000;
        user = buildMonWithWeight(userWeight);
        target = buildMonWithWeight(userWeight);

        final double basePower = move.getPower(user, target, battle);
        assertThat(basePower).isEqualTo(40);
        target.setHeldItem(FLOAT_STONE);
        assertThat(move.getPower(user, target, battle)).isEqualTo(60);
        target.setAbility(LIGHT_METAL);
        assertThat(move.getPower(user, target, battle)).isEqualTo(100);
        target.setAbility(HEAVY_METAL);
        assertThat(move.getPower(user, target, battle)).isEqualTo(40);
    }

    @Test
    void testAutomotizeIsSubtractedFromWeightBeforeDoublingOrHalving() {
        final double baseWeight = 160;
        target = buildMonWithWeight(baseWeight);
        target.addVolatileStatus(new AutotomizedStatus());
        target.setHeldItem(FLOAT_STONE);

        //This makes sure that the weight will be (160-100)/2, instead of (160/2)-100.
        assertThat(target.getWeight()).isEqualTo(30);

        target.setHeldItem(NO_ITEM);
        target.setAbility(HEAVY_METAL);
        assertThat(target.getWeight()).isEqualTo(120);

        target = buildMonWithWeight(90);
        target.addVolatileStatus(new AutotomizedStatus());
        target.setAbility(LIGHT_METAL);
        assertThat(target.getWeight()).isEqualTo(.1);

        target.setAbility(HEAVY_METAL);
        assertThat(target.getWeight()).isEqualTo(.2);
    }
}
