package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.abilities.Technician;
import org.shorts.model.moves.multihit.MultiHitMove;
import org.shorts.model.moves.priority.plusOne.BulletPunch;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.model.abilities.Technician.TECHNICIAN;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class TechnicianBoostTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        user.setAbility(TECHNICIAN);
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        Main.DAMAGE_RANDOM = MAX_RANDOM;
    }

    @Test
    void testFiftyPercentPowerBoostForTechnician() {
        Move weakMove = new BulletPunch();
        final double basePower = weakMove.getPower(
            user,
            target,
            battle);
        assertThat(basePower).isLessThanOrEqualTo(Technician.BASE_POWER_THRESHOLD);
        assertThat(weakMove.calculateMovePower(
            user,
            target,
            battle)).isEqualTo(Technician.MULTIPLIER * basePower);
        assertThat(weakMove.calculateMovePower(target, user, battle)).isEqualTo(basePower);
    }

    @Test
    void testOnlyActivatesFor60PowerOrLower() {
        Move weakMove = new Tackle();
        final double weakMovePower = weakMove.getPower(user, target, battle);
        assertThat(weakMovePower).isLessThan(Technician.BASE_POWER_THRESHOLD);
        assertThat(weakMove.calculateMovePower(user, target, battle)).isEqualTo(
            Technician.MULTIPLIER * weakMovePower);

        Move power60 = new Bulldoze();
        final double sixty = power60.getPower(user, target, battle);
        assertThat(sixty).isEqualTo(Technician.BASE_POWER_THRESHOLD);
        assertThat(power60.calculateMovePower(user, target, battle)).isEqualTo(
            Technician.MULTIPLIER * sixty);

        Move tooStrong = new Earthquake();
        final double tooStrongPower = tooStrong.getPower(user, target, battle);
        assertThat(tooStrongPower).isGreaterThan(Technician.BASE_POWER_THRESHOLD);
        assertThat(tooStrong.calculateMovePower(user, target, battle)).isEqualTo(tooStrongPower);
    }

    @Test
    void testActivatesForEachHitOfMultiHitMoves() {
        final Move multiHitMove = new MultiHitMove(
            "test two hits",
            60,
            100,
            Type.NORMAL,
            Move.Category.PHYSICAL,
            Range.NORMAL,
            8,
            true,
            0,
            2,
            2) {

        };

        multiHitMove.executeOnTarget(user, target, battle);
        multiHitMove.executeOnTarget(target, user, battle);
        final double technicianBoostedDamage = target.getHpDiff();
        final double regularDamage = user.getHpDiff();
        assertThat(technicianBoostedDamage).isEqualTo(regularDamage * Technician.MULTIPLIER);
    }

}
