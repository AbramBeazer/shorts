package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.model.types.Type;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.types.Type.*;


class FlyingPressTests {

    private FlyingPress flyingPress;
    @BeforeEach
    void setup(){
        flyingPress = new FlyingPress();
    }
    @Test
    void testFlyingDoubleFightingHalfEqualsNeutral() {
        assertThat(flyingPress.getBaseTypeMultiplier(Set.of(GRASS, POISON))).isEqualTo(Type.NEUTRAL);
    }

    @Test
    void testFightingDoubleFlyingHalfEqualsNeutral() {
        assertThat(flyingPress.getBaseTypeMultiplier(Set.of(STEEL))).isEqualTo(Type.NEUTRAL);
    }

    @Test
    void testFlyingDoubleFightingNeutralEqualsDouble() {
        assertThat(flyingPress.getBaseTypeMultiplier(Set.of(FIGHTING))).isEqualTo(SUPER_EFFECTIVE);
    }

    @Test
    void testFightingDoubleFlyingNeutralEqualsDouble() {
        assertThat(flyingPress.getBaseTypeMultiplier(Set.of(NORMAL))).isEqualTo(SUPER_EFFECTIVE);
    }

    @Test
    void testFlyingNeutralFightingHalfEqualsHalf(){
        assertThat(flyingPress.getBaseTypeMultiplier(Set.of(POISON))).isEqualTo(NOT_VERY_EFFECTIVE);
    }
    @Test
    void testFightingNeutralFlyingHalfEqualsHalf() {
        assertThat(flyingPress.getBaseTypeMultiplier(Set.of(ELECTRIC))).isEqualTo(NOT_VERY_EFFECTIVE);
    }
    @Test
    void testBothHalfEqualsQuarter() {
        assertThat(flyingPress.getBaseTypeMultiplier(Set.of(POISON, ELECTRIC))).isEqualTo(QUAD_RESIST);
    }

    @Test
    void testBothDoubleEqualsQuadruple() {
        assertThat(flyingPress.getBaseTypeMultiplier(Set.of(GRASS, ICE))).isEqualTo(QUAD_EFFECTIVE);
    }


}
