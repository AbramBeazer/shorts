package org.shorts.model.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.model.Nature;
import org.shorts.model.pokemon.Pokedex;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.BeastBoost.BEAST_BOOST;

class AutotomizedStatusTests {

    private Pokemon user;

    @BeforeAll
    static void createDex() throws Exception {
        Pokedex.create();
    }

    @BeforeEach
    void setUp() {
        user = new Pokemon(Pokedex.get("Celesteela"), 100, new int[] { 0, 0, 0, 0, 0, 0 }, Nature.SERIOUS, BEAST_BOOST);
    }

    @Test
    void testWeightIsLowered100KgForEachLevel() {
        final double originalWeight = user.getWeight();

        final AutotomizedStatus autotomizedStatus = new AutotomizedStatus();
        assertThat(autotomizedStatus.getLevels()).isOne();

        user.addVolatileStatus(autotomizedStatus);
        assertThat(user.getWeight()).isEqualTo(originalWeight - 100);

        autotomizedStatus.incrementLevels();
        assertThat(user.getWeight()).isEqualTo(originalWeight - 200);
    }

    @Test
    void testWeightCannotGoBelowZeroPointOneKg() {
        final AutotomizedStatus autotomizedStatus = new AutotomizedStatus();
        assertThat(autotomizedStatus.getLevels()).isOne();

        assertThat(user.getWeight()).isLessThan(1000);

        for (int i = 0; i < 15; i++) {
            autotomizedStatus.incrementLevels();
        }

        user.addVolatileStatus(autotomizedStatus);

        assertThat(user.getWeight()).isEqualTo(0.1);
    }
}
