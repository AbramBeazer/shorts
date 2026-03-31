package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.abilities.Technician;
import org.shorts.model.moves.priority.plusOne.BulletPunch;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Scizor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.PinchTypeBoostAbility.SWARM;
import static org.shorts.model.abilities.Technician.TECHNICIAN;

class TechnicianBoostTests {

    private Battle battle;
    private final Pokemon hasTechnician = new Scizor(TECHNICIAN);
    private final Pokemon hasOtherAbility = new Scizor(SWARM);

    @BeforeEach
    void setUp() {
        battle = new Battle(
            new Trainer("Red", List.of(hasTechnician)),
            new Trainer("Green", List.of(hasOtherAbility)), 1);
    }

    @Test
    void testFiftyPercentPowerBoostForTechnician() {
        Move weakMove = new BulletPunch();
        final double basePower = weakMove.getPower(
            hasTechnician,
            hasOtherAbility,
            battle);
        assertThat(basePower).isLessThanOrEqualTo(Technician.BASE_POWER_THRESHOLD);
        assertThat(weakMove.calculateMovePower(
            hasTechnician,
            hasOtherAbility,
            battle)).isEqualTo(Technician.MULTIPLIER * basePower);
        assertThat(weakMove.calculateMovePower(hasOtherAbility, hasTechnician, battle)).isEqualTo(basePower);
    }

    @Test
    void testOnlyActivatesFor60PowerOrLower() {
        Move weakMove = new Tackle();
        final double weakMovePower = weakMove.getPower(hasTechnician, hasOtherAbility, battle);
        assertThat(weakMovePower).isLessThan(Technician.BASE_POWER_THRESHOLD);
        assertThat(weakMove.calculateMovePower(hasTechnician, hasOtherAbility, battle)).isEqualTo(
            Technician.MULTIPLIER * weakMovePower);

        Move power60 = new Bulldoze();
        final double sixty = power60.getPower(hasTechnician, hasOtherAbility, battle);
        assertThat(sixty).isEqualTo(Technician.BASE_POWER_THRESHOLD);
        assertThat(power60.calculateMovePower(hasTechnician, hasOtherAbility, battle)).isEqualTo(
            Technician.MULTIPLIER * sixty);

        Move tooStrong = new Earthquake();
        final double tooStrongPower = tooStrong.getPower(hasTechnician, hasOtherAbility, battle);
        assertThat(tooStrongPower).isGreaterThan(Technician.BASE_POWER_THRESHOLD);
        assertThat(tooStrong.calculateMovePower(hasTechnician, hasOtherAbility, battle)).isEqualTo(tooStrongPower);
    }

}
