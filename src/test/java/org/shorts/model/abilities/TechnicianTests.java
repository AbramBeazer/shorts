package org.shorts.model.abilities;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.SingleBattle;
import org.shorts.battle.Trainer;
import org.shorts.model.moves.Bulldoze;
import org.shorts.model.moves.Earthquake;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Tackle;
import org.shorts.model.moves.priority.plusOne.BulletPunch;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Scizor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.PinchTypeBoostAbility.SWARM;
import static org.shorts.model.abilities.Technician.TECHNICIAN;

class TechnicianTests {

    private Battle battle;
    private final Pokemon hasTechnician = new Scizor(TECHNICIAN);
    private final Pokemon hasOtherAbility = new Scizor(SWARM);

    @BeforeEach
    void setUp() {
        battle = new SingleBattle(
            new Trainer("Red", List.of(hasTechnician)),
            new Trainer("Green", List.of(hasOtherAbility)));
    }

    @Test
    void testFiftyPercentPowerBoostForTechnician() {
        Move weakMove = new BulletPunch();
        assertThat(weakMove.getPower(
            hasTechnician,
            hasOtherAbility,
            battle)).isLessThanOrEqualTo(Technician.BASE_POWER_THRESHOLD);
        assertThat(hasTechnician.getAbility()
            .getMovePowerMultipliers(
                hasTechnician,
                hasOtherAbility,
                battle,
                weakMove)).isEqualTo(Technician.MULTIPLIER);
        assertThat(hasOtherAbility.getAbility()
            .getMovePowerMultipliers(hasOtherAbility, hasTechnician, battle, weakMove)).isEqualTo(1);
    }

    @Test
    void testOnlyActivatesFor60PowerOrLower() {
        Move weakMove = new Tackle();
        assertThat(weakMove.getPower(
            hasTechnician,
            hasOtherAbility,
            battle)).isLessThan(Technician.BASE_POWER_THRESHOLD);
        assertThat(TECHNICIAN.getMovePowerMultipliers(hasTechnician, hasOtherAbility, battle, weakMove)).isEqualTo(
            Technician.MULTIPLIER);

        Move power60 = new Bulldoze();
        assertThat(power60.getPower(
            hasTechnician,
            hasOtherAbility,
            battle)).isEqualTo(Technician.BASE_POWER_THRESHOLD);
        assertThat(TECHNICIAN.getMovePowerMultipliers(hasTechnician, hasOtherAbility, battle, power60)).isEqualTo(
            Technician.MULTIPLIER);

        Move tooStrong = new Earthquake();
        assertThat(tooStrong.getPower(
            hasTechnician,
            hasOtherAbility,
            battle)).isGreaterThan(Technician.BASE_POWER_THRESHOLD);
        assertThat(TECHNICIAN.getMovePowerMultipliers(hasTechnician, hasOtherAbility, battle, tooStrong)).isEqualTo(1);
    }

}
