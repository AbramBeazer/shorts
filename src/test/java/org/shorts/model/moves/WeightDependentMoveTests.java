package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.pokemon.PokedexEntry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.DummyAbility.DUMMY_ABILITY;
import static org.shorts.model.abilities.HeavyMetal.HEAVY_METAL;
import static org.shorts.model.abilities.LightMetal.LIGHT_METAL;
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

    private Pokemon buildMonWithWeight(int weight) {
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
    //
    //    @Test
    //    void
}
