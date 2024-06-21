package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.MockRandomReturnZero;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.abilities.Protosynthesis;
import org.shorts.model.abilities.QuarkDrive;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.PokemonTestUtils;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.Klutz.KLUTZ;
import static org.shorts.model.items.BoosterEnergy.BOOSTER_ENERGY;
import static org.shorts.model.items.Leftovers.LEFTOVERS;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.PrimalOrb.RED_ORB;

class FlingTests {

    private Fling fling;
    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() {
        fling = new Fling();
        user = PokemonTestUtils.getDummyPokemon();
        target = PokemonTestUtils.getDummyPokemon();
        battle = new DummySingleBattle();
        Main.RANDOM = new MockRandomReturnZero();
    }

    @Test
    void failsWithNoHeldItem() {
        user.setHeldItem(NO_ITEM);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsWhenFlingPowerIsZero() {
        user.setHeldItem(RED_ORB);
        assertThat(user.getHeldItem().getFlingPower()).isZero();

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsWhenUserAbilityIsKlutz() {
        user.setHeldItem(LEFTOVERS);
        user.setAbility(KLUTZ);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsWhenMagicRoomIsOn() {
        user.setHeldItem(LEFTOVERS);
        battle.setMagicRoomTurns(1);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsWhenUserIsEmbargoed() {
        user.setHeldItem(LEFTOVERS);
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.EMBARGOED, 1));

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsWhenUserHasBoosterEnergyAndProtosynthesis() {
        user.setHeldItem(BOOSTER_ENERGY);
        user.setAbility(new Protosynthesis());

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsWhenUserHasBoosterEnergyAndQuarkDrive() {
        user.setHeldItem(BOOSTER_ENERGY);
        user.setAbility(new QuarkDrive());

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsButConsumesItemWhenTargetIsProtected() {
        user.setHeldItem(LEFTOVERS);

        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.PROTECTED, 1));

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(LEFTOVERS);
    }
}
