package org.shorts.battle;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SeededTests {

    @Test
    void testDealsDamageAndHeals() {
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotActivateIfSeederSpotIsVacant() {
        assertThat(false).isTrue();
    }

    @Test
    void testIsTransferredByBatonPass() {
        assertThat(false).isTrue();
    }

    @Test
    void testEffectCarriesOverIfSeederSpotIsVacatedAndThenFilled() {
        assertThat(false).isTrue();
    }

    @Test
    void testEffectCarriesOverIfSeederSwitchesAndAfflictedUsesBatonPass() {
        assertThat(false).isTrue();
    }

    @Test
    void testBigRootHealsMore() {
        assertThat(false).isTrue();
    }

    @Test
    void testLiquidOozeCausesDamage() {
        assertThat(false).isTrue();
    }

    @Test
    void testDamageIsDealtButHealingDoesNotOccurWhenHealBlocked() {
        assertThat(false).isTrue();
    }

    @Test
    void testDamageIsDealtAndLiquidOozeDamageIsDealtWhenHealBlocked() {
        assertThat(false).isTrue();
    }

    //TODO: TEST THIS AND MAKE SURE IT STILL APPLIES:
    // If the target is seeded during a round that it switches in, it will take recurrent damage during that round, but only if its speed is less than or equal to the user's speed.

}
