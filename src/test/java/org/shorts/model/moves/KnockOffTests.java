package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.items.DriveItem;
import org.shorts.model.items.HeldItem;
import org.shorts.model.items.PlateItem;
import org.shorts.model.pokemon.Arceus;
import org.shorts.model.pokemon.Genesect;
import org.shorts.model.pokemon.Giratina;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.SubstituteStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.Pressure.PRESSURE;
import static org.shorts.model.abilities.StickyHold.STICKY_HOLD;
import static org.shorts.model.items.GriseousOrb.GRISEOUS_ORB;
import static org.shorts.model.items.Leftovers.LEFTOVERS;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.PlateItem.DRACO_PLATE;
import static org.shorts.model.items.PlateItem.PIXIE_PLATE;
import static org.shorts.model.items.PlateItem.STONE_PLATE;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class KnockOffTests {

    private KnockOff knockOff;
    private final Battle battle = new DummySingleBattle();

    @BeforeEach
    void setUp() {
        knockOff = new KnockOff();
    }

    @Test
    void testFocusBandShouldActivateBeforeBeingLost() {
        assertThat(false).isTrue();

    }

    @Test
    void testFocusSashShouldActivateBeforeBeingLost() {
        assertThat(false).isTrue();

    }

    @Test
    void testColburBerryShouldActivateBeforeBeingLostButDamageShouldBeBoosted() {
        assertThat(false).isTrue();

    }

    @Test
    void testDoesNotWorkOnGiratinaWithGriseousOrb() {
        Giratina target = new Giratina(PRESSURE);
        target.setHeldItem(LEFTOVERS);
        Pokemon attacker = getDummyPokemon();
        assertThat(knockOff.calculateMovePower(attacker, target, battle)).isEqualTo(KnockOff.MULTIPLIER);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);

        final HeldItem keepItem = GRISEOUS_ORB;
        target.setHeldItem(keepItem);
        assertThat(knockOff.calculateMovePower(attacker, target, battle)).isEqualTo(1);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(keepItem);
    }

    @Test
    void testGiratinaCannotKnockOffGriseousOrb() {
        Pokemon target = getDummyPokemon();
        Giratina attacker = new Giratina(PRESSURE);

        final HeldItem item = GRISEOUS_ORB;
        target.setHeldItem(item);
        assertThat(knockOff.calculateMovePower(attacker, target, battle)).isEqualTo(1);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(item);
    }

    @Test
    void testDoesNotWorkOnArceusWithPlate() {
        Arceus target = new Arceus();
        target.setHeldItem(LEFTOVERS);
        Pokemon attacker = getDummyPokemon();
        assertThat(knockOff.calculateMovePower(attacker, target, battle)).isEqualTo(KnockOff.MULTIPLIER);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);

        PlateItem keepItem = STONE_PLATE;
        target.setHeldItem(keepItem);
        assertThat(knockOff.calculateMovePower(attacker, target, battle)).isEqualTo(1);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(keepItem);
    }

    @Test
    void testArceusCannotKnockOffPlate() {
        Pokemon target = getDummyPokemon();
        Arceus attacker = new Arceus();

        final PlateItem plate = PIXIE_PLATE;
        target.setHeldItem(plate);
        assertThat(knockOff.calculateMovePower(attacker, target, battle)).isEqualTo(1);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(plate);
    }

    @Test
    void testDoesNotWorkOnGenesectWithDrive() {
        Genesect target = new Genesect();
        target.setHeldItem(LEFTOVERS);
        Pokemon attacker = getDummyPokemon();
        assertThat(knockOff.calculateMovePower(attacker, target, battle)).isEqualTo(KnockOff.MULTIPLIER);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);

        DriveItem keepItem = DriveItem.SHOCK_DRIVE;
        target.setHeldItem(keepItem);
        assertThat(knockOff.calculateMovePower(attacker, target, battle)).isEqualTo(1);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(keepItem);
    }

    @Test
    void testGenesectCannotKnockOffDrive() {
        Pokemon target = getDummyPokemon();
        Genesect attacker = new Genesect();

        final DriveItem drive = DriveItem.SHOCK_DRIVE;
        target.setHeldItem(drive);
        assertThat(knockOff.calculateMovePower(attacker, target, battle)).isEqualTo(1);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(drive);
    }

    @Test
    void testOthersCanKnockOffGriseousOrbPlateAndDrive() {
        Pokemon attacker = getDummyPokemon();
        Pokemon target = getDummyPokemon();

        target.setHeldItem(GRISEOUS_ORB);
        assertThat(knockOff.calculateMovePower(attacker, target, battle)).isEqualTo(KnockOff.MULTIPLIER);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);

        target.setHeldItem(DRACO_PLATE);
        assertThat(knockOff.calculateMovePower(attacker, target, battle)).isEqualTo(KnockOff.MULTIPLIER);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);

        target.setHeldItem(DriveItem.CHILL_DRIVE);
        assertThat(knockOff.calculateMovePower(attacker, target, battle)).isEqualTo(KnockOff.MULTIPLIER);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);
    }

    @Test
    void testDoesNotWorkOnZCrystal() {

    }

    @Test
    void testDoesNotWorkOnCorrectMegaStone() {
        assertThat(false).isTrue();

    }

    @Test
    void testWorksOnIncorrectMegaStone() {
        assertThat(false).isTrue();

    }

    @Test
    void testDoesNotWorkOnCorrectPrimalOrb() {
        assertThat(false).isTrue();

    }

    @Test
    void testWorksOnIncorrectPrimalOrb() {
        assertThat(false).isTrue();

    }

    @Test
    void testDoesNotWorkOnSilvallyWithMemory() {
        assertThat(false).isTrue();

    }

    @Test
    void testDoesNotWorkOnZacianWithRustedSword() {
        assertThat(false).isTrue();

    }

    @Test
    void testDoesNotWorkOnZamazentaWithRustedShield() {
        assertThat(false).isTrue();

    }

    @Test
    void testDoesNotWorkOnBoosterEnergy() {
        assertThat(false).isTrue();

    }

    @Test
    void testWorksOnBoosterEnergyForKoraidonAndMiraidon() {
        assertThat(false).isTrue();

    }

    @Test
    void testDamageBoostAppliesToStickyHoldButItemIsNotLost() {
        final Pokemon attacker = getDummyPokemon();
        final Pokemon defender = getDummyPokemon();
        defender.setAbility(STICKY_HOLD);
        final HeldItem item = LEFTOVERS;
        defender.setHeldItem(item);

        assertThat(knockOff.calculateMovePower(attacker, defender, battle)).isEqualTo(KnockOff.MULTIPLIER);
        knockOff.trySecondaryEffect(attacker, defender, battle);
        assertThat(defender.getHeldItem()).isEqualTo(item);
    }

    @Test
    void testDamageBoostAppliesToSubstituteButItemIsNotLost() {
        final Pokemon attacker = getDummyPokemon();
        final Pokemon defender = getDummyPokemon();
        defender.addVolatileStatus(new SubstituteStatus(1));
        final HeldItem item = LEFTOVERS;
        defender.setHeldItem(item);

        assertThat(knockOff.calculateMovePower(attacker, defender, battle)).isEqualTo(KnockOff.MULTIPLIER);
        knockOff.trySecondaryEffect(attacker, defender, battle);
        assertThat(defender.getHeldItem()).isEqualTo(item);
    }

}
