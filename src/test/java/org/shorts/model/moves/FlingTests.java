package org.shorts.model.moves;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FlingTests {

    @Test
    void failsWithNoHeldItem() {
        assertThat(false).isTrue();
    }

    @Test
    void failsWhenFlingPowerIsZero() {
        assertThat(false).isTrue();
    }

    @Test
    void failsButConsumesItemWhenTargetIsProtected() {
        assertThat(false).isTrue();
    }
}
