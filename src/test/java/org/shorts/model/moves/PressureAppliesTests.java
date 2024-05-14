package org.shorts.model.moves;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.shorts.model.moves.entryhazardsetter.StealthRock;
import org.shorts.model.moves.entryhazardsetter.StickyWeb;
import org.shorts.model.pokemon.Groudon;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.types.Type.GHOST;

class PressureAppliesTests {

    private Pokemon attacker = new Groudon();

    //    @BeforeEach
    //    void setup() {
    //        this.attacker
    //    }

    @Test
    void testReturnsTrueForPhysicalAttack() {
        PhysicalMove move = new Earthquake();
        assertThat(move.pressureApplies(attacker)).isTrue();
    }

    @Test
    void testReturnsTrueForSpecialAttack() {
        SpecialMove move = new Scald();
        assertThat(move.pressureApplies(attacker)).isTrue();
    }

    @Test
    void testReturnsTrueForStatusMoveThatTargetsOthers() {
        StatusMove move = new StealthRock();
        assertThat(move.pressureApplies(attacker)).isTrue();
    }

    @Test
    void testReturnsFalseForStatusMoveThatOnlyTargetsSelf() {
        StatusMove move = new Rest();
        assertThat(move.pressureApplies(attacker)).isFalse();
    }

    @Test
    void testReturnsFalseForStickyWeb() {
        StatusMove move = new StickyWeb();
        assertThat(move.pressureApplies(attacker)).isFalse();
    }

    @Test
    void testCurse() {
        Curse curse = new Curse();
        assertThat(attacker.getTypes()).doesNotContain(GHOST);
        assertThat(curse.pressureApplies(attacker)).isFalse();
        attacker.setTypes(Set.of(GHOST));
        assertThat(curse.pressureApplies(attacker)).isTrue();
    }
}
