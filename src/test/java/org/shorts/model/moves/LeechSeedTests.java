package org.shorts.model.moves;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LeechSeedTests {

    @Test
    void testFailsOnGrassTarget() {
        assertThat(false).isTrue();
    }

    @Test
    void testFailsOnSubstitute() {
        assertThat(false).isTrue();
    }

    @Test
    void testFailsIfTargetIsAlreadySeeded() {
        assertThat(false).isTrue();
    }
}
