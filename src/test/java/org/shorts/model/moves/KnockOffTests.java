package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.items.DriveItem;
import org.shorts.model.items.GriseousOrb;
import org.shorts.model.items.HeldItem;
import org.shorts.model.items.MegaStone;
import org.shorts.model.items.MemoryItem;
import org.shorts.model.items.PlateItem;
import org.shorts.model.items.PrimalOrb;
import org.shorts.model.items.RustedShield;
import org.shorts.model.items.RustedSword;
import org.shorts.model.items.ZCrystal;
import org.shorts.model.pokemon.Arceus;
import org.shorts.model.pokemon.Genesect;
import org.shorts.model.pokemon.Giratina;
import org.shorts.model.pokemon.Groudon;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Silvally;
import org.shorts.model.pokemon.Tyranitar;
import org.shorts.model.pokemon.Zacian;
import org.shorts.model.pokemon.Zamazenta;
import org.shorts.model.status.SubstituteStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.Pressure.PRESSURE;
import static org.shorts.model.abilities.StickyHold.STICKY_HOLD;
import static org.shorts.model.abilities.weather.WeatherAbility.SAND_STREAM;
import static org.shorts.model.items.DriveItem.CHILL_DRIVE;
import static org.shorts.model.items.GriseousOrb.GRISEOUS_ORB;
import static org.shorts.model.items.Leftovers.LEFTOVERS;
import static org.shorts.model.items.MegaStone.TYRANITARITE;
import static org.shorts.model.items.MemoryItem.GHOST_MEMORY;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.PlateItem.SPLASH_PLATE;
import static org.shorts.model.items.PrimalOrb.RED_ORB;
import static org.shorts.model.items.RustedShield.RUSTED_SHIELD;
import static org.shorts.model.items.RustedSword.RUSTED_SWORD;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class KnockOffTests {

    private KnockOff knockOff;
    private final Battle battle = new DummySingleBattle();

    @BeforeEach
    void setUp() {
        knockOff = new KnockOff();
    }

    void testPersonalItemCannotBeKnockedOffButOtherItemsCan(Pokemon target, HeldItem keepItem) {
        target.setHeldItem(LEFTOVERS);
        Pokemon attacker = getDummyPokemon();
        assertThat(knockOff.getPowerMultipliers(attacker, target, battle)).isEqualTo(KnockOff.MULTIPLIER);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);

        target.setHeldItem(keepItem);
        assertThat(knockOff.getPowerMultipliers(attacker, target, battle)).isEqualTo(1);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(keepItem);
    }

    void testCannotKnockOffOwnPersonalItemHeldByDifferentSpecies(Pokemon attacker, HeldItem keepItem) {
        Pokemon target = getDummyPokemon();
        target.setHeldItem(keepItem);

        assertThat(knockOff.getPowerMultipliers(attacker, target, battle)).isEqualTo(1);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(keepItem);
    }

    void testOthersCanKnockOffPokemonSpecificItems(HeldItem item) {
        Pokemon attacker = getDummyPokemon();
        Pokemon target = getDummyPokemon();

        target.setHeldItem(item);
        assertThat(knockOff.getPowerMultipliers(attacker, target, battle)).isEqualTo(KnockOff.MULTIPLIER);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(NO_ITEM);
    }

    @Test
    void testGiratinaAndGriseousOrb() {
        final Giratina mon = new Giratina(PRESSURE);
        final GriseousOrb item = GRISEOUS_ORB;
        testPersonalItemCannotBeKnockedOffButOtherItemsCan(mon, item);
        testCannotKnockOffOwnPersonalItemHeldByDifferentSpecies(mon, item);
        testOthersCanKnockOffPokemonSpecificItems(item);
    }

    @Test
    void testArceusAndPlate() {
        final Arceus mon = new Arceus();
        final PlateItem item = SPLASH_PLATE;
        testPersonalItemCannotBeKnockedOffButOtherItemsCan(mon, item);
        testCannotKnockOffOwnPersonalItemHeldByDifferentSpecies(mon, item);
        testOthersCanKnockOffPokemonSpecificItems(item);
    }

    @Test
    void testGenesectAndDrive() {
        final Genesect mon = new Genesect();
        final DriveItem item = CHILL_DRIVE;
        testPersonalItemCannotBeKnockedOffButOtherItemsCan(mon, item);
        testCannotKnockOffOwnPersonalItemHeldByDifferentSpecies(mon, item);
        testOthersCanKnockOffPokemonSpecificItems(item);
    }

    @Test
    void testSilvallyAndMemory() {
        final Silvally mon = new Silvally();
        final MemoryItem item = GHOST_MEMORY;
        testPersonalItemCannotBeKnockedOffButOtherItemsCan(mon, item);
        testCannotKnockOffOwnPersonalItemHeldByDifferentSpecies(mon, item);
        testOthersCanKnockOffPokemonSpecificItems(item);
    }

    @Test
    void testZacianAndRustedSword() {
        final Zacian mon = new Zacian();
        final RustedSword item = RUSTED_SWORD;
        testPersonalItemCannotBeKnockedOffButOtherItemsCan(mon, item);
        testCannotKnockOffOwnPersonalItemHeldByDifferentSpecies(mon, item);
        testOthersCanKnockOffPokemonSpecificItems(item);
    }

    @Test
    void testZamazentaAndRustedShield() {
        final Zamazenta mon = new Zamazenta();
        final RustedShield item = RUSTED_SHIELD;
        testPersonalItemCannotBeKnockedOffButOtherItemsCan(mon, item);
        testCannotKnockOffOwnPersonalItemHeldByDifferentSpecies(mon, item);
        testOthersCanKnockOffPokemonSpecificItems(item);
    }

    @Test
    void testMegaStone() {
        final Tyranitar mon = new Tyranitar(SAND_STREAM);
        final MegaStone item = TYRANITARITE;

        testPersonalItemCannotBeKnockedOffButOtherItemsCan(mon, item);
        testCannotKnockOffOwnPersonalItemHeldByDifferentSpecies(mon, item);
        testOthersCanKnockOffPokemonSpecificItems(item);
    }

    @Test
    void testPrimalOrb() {
        final Groudon mon = new Groudon();
        final PrimalOrb item = RED_ORB;

        testPersonalItemCannotBeKnockedOffButOtherItemsCan(mon, item);
        testCannotKnockOffOwnPersonalItemHeldByDifferentSpecies(mon, item);
        testOthersCanKnockOffPokemonSpecificItems(item);
    }

    @Test
    void testDoesNotWorkOnZCrystal() {
        Pokemon attacker = getDummyPokemon();
        Pokemon target = getDummyPokemon();

        final ZCrystal zCrystal = ZCrystal.BUGINIUM_Z;
        target.setHeldItem(zCrystal);
        assertThat(knockOff.getPowerMultipliers(attacker, target, battle)).isEqualTo(1);
        knockOff.trySecondaryEffect(attacker, target, battle);
        assertThat(target.getHeldItem()).isEqualTo(zCrystal);
    }

    @Test
    void testDoesNotWorkOnProtoSynthesisOrQuarkDrivePokemonWithBoosterEnergy() {
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

        assertThat(knockOff.getPowerMultipliers(attacker, defender, battle)).isEqualTo(KnockOff.MULTIPLIER);
        knockOff.trySecondaryEffect(attacker, defender, battle);
        assertThat(defender.getHeldItem()).isEqualTo(item);
    }

    @Test
    void testLoseItemWhenStickyHoldPokemonFaints() {
        final Pokemon attacker = getDummyPokemon();
        final Pokemon defender = getDummyPokemon();

        defender.setAbility(STICKY_HOLD);
        defender.setHeldItem(LEFTOVERS);

        assertThat(knockOff.getPowerMultipliers(attacker, defender, battle)).isEqualTo(KnockOff.MULTIPLIER);
        defender.setCurrentHP(0);
        knockOff.trySecondaryEffect(attacker, defender, battle);
        assertThat(defender.getHeldItem()).isEqualTo(NO_ITEM);
    }

    @Test
    void testDamageBoostAppliesToSubstituteButItemIsNotLost() {
        final Pokemon attacker = getDummyPokemon();
        final Pokemon defender = getDummyPokemon();
        defender.addVolatileStatus(new SubstituteStatus(1));
        final HeldItem item = LEFTOVERS;
        defender.setHeldItem(item);

        assertThat(knockOff.getPowerMultipliers(attacker, defender, battle)).isEqualTo(KnockOff.MULTIPLIER);
        knockOff.trySecondaryEffect(attacker, defender, battle);
        assertThat(defender.getHeldItem()).isEqualTo(item);
    }

    @Test
    void testNoSecondaryEffectIfUserHasFainted() {
        final Pokemon attacker = getDummyPokemon();
        final Pokemon defender = getDummyPokemon();

        final HeldItem item = LEFTOVERS;
        defender.setHeldItem(item);
        assertThat(knockOff.getPowerMultipliers(attacker, defender, battle)).isEqualTo(KnockOff.MULTIPLIER);
        attacker.setCurrentHP(0);
        knockOff.trySecondaryEffect(attacker, defender, battle);
        assertThat(defender.getHeldItem()).isEqualTo(item);
    }

    @Test
    void testNoBoostIfTargetHasNoItem() {
        final Pokemon attacker = getDummyPokemon();
        final Pokemon defender = getDummyPokemon();

        assertThat(defender.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(knockOff.getPowerMultipliers(attacker, defender, battle)).isEqualTo(1);
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
    void testRegularBerry() {
        assertThat(false).isTrue();
        //TODO: What do I do here? Would a Sitrus Berry activate or would it be knocked off?
    }
}
