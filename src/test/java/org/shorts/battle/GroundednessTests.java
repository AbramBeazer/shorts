package org.shorts.battle;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GroundednessTests {

    @Test
    void testIronBallOnFlyingTypeWithNoOtherGrounding() {
        assertThat(false).isEqualTo(true);
    }

    @Test
    void testGroundedFlyingTypeWithNoIronBall() {
        assertThat(false).isEqualTo(true);
    }

    @Test
    void testIronBallOnFlyingTypeWithAdditionalGroundingEffects() {
        assertThat(false).isEqualTo(true);
    }

    @Test
    void testHazardsApplyOnHolderOfIronBall() {
        assertThat(false).isEqualTo(true);
    }
}
