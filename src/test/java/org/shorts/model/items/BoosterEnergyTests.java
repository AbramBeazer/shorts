package org.shorts.model.items;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoosterEnergyTests {

    @Test
    void testIsNotConsumedIfHolderHasProtosynthesisAndSunIsOut() {
        assertThat(false).isTrue();
    }

    @Test
    void testIsNotConsumedIfHolderHasProtosynthesisAndExtremeSunIsOut() {
        assertThat(false).isTrue();
    }

    @Test
    void testActivatesIfWeatherIsSuppressed() {
        assertThat(false).isTrue();
    }

    @Test
    void testIsNotConsumedIfHolderHasQuarkDriveAndElectricTerrainApplies() {
        assertThat(false).isTrue();
    }
}
