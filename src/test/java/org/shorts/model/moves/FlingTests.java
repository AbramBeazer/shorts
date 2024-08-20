package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.abilities.Protosynthesis;
import org.shorts.model.abilities.QuarkDrive;
import org.shorts.model.items.GriseousOrb;
import org.shorts.model.items.MegaStone;
import org.shorts.model.items.MemoryItem;
import org.shorts.model.items.WhiteHerb;
import org.shorts.model.pokemon.Pokedex;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.PokemonTestUtils;
import org.shorts.model.status.Status;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.abilities.Klutz.KLUTZ;
import static org.shorts.model.abilities.Unnerve.UNNERVE;
import static org.shorts.model.items.BoosterEnergy.BOOSTER_ENERGY;
import static org.shorts.model.items.DriveItem.SHOCK_DRIVE;
import static org.shorts.model.items.FlameOrb.FLAME_ORB;
import static org.shorts.model.items.Gem.DARK_GEM;
import static org.shorts.model.items.KingsRock.KINGS_ROCK;
import static org.shorts.model.items.Leftovers.LEFTOVERS;
import static org.shorts.model.items.LifeOrb.LIFE_ORB;
import static org.shorts.model.items.LightBall.LIGHT_BALL;
import static org.shorts.model.items.LoadedDice.LOADED_DICE;
import static org.shorts.model.items.MentalHerb.MENTAL_HERB;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.PlateItem.SPLASH_PLATE;
import static org.shorts.model.items.PrimalOrb.RED_ORB;
import static org.shorts.model.items.RazorFang.RAZOR_FANG;
import static org.shorts.model.items.RustedShield.RUSTED_SHIELD;
import static org.shorts.model.items.RustedSword.RUSTED_SWORD;
import static org.shorts.model.items.ToxicOrb.TOXIC_ORB;
import static org.shorts.model.items.TypeBoostItem.BLACK_GLASSES;
import static org.shorts.model.items.TypeBoostItem.POISON_BARB;
import static org.shorts.model.items.berries.OranBerry.ORAN_BERRY;
import static org.shorts.model.items.berries.SitrusBerry.SITRUS_BERRY;
import static org.shorts.model.status.Status.BURN;
import static org.shorts.model.status.Status.NONE;
import static org.shorts.model.status.Status.PARALYZE;
import static org.shorts.model.status.Status.TOXIC_POISON;

class FlingTests {

    private Fling fling;
    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() throws Exception {
        //TODO: There has to be a better way than loading the whole Pokédex. The only reason I do it this way is because we check for a user/item match by comparing with the species in the dex entry, to allow Zacian-Crowned and regular Zacian to both appear as Zacian, for example.
        Pokedex.create();
        fling = new Fling();
        user = PokemonTestUtils.getDummyPokemon();
        target = PokemonTestUtils.getDummyPokemon();
        battle = new DummyBattle(user, target);
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
    }

    @Test
    void testItemIsLostAndConsumedWhenAttackSucceeds() {
        user.setHeldItem(LEFTOVERS);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(LEFTOVERS);
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
    void failsButConsumesItemWhenTargetIsProtected() {
        user.setHeldItem(FLAME_ORB);

        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.PROTECTED, 1));

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
        assertThat(target.getStatus()).isEqualTo(NONE);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(FLAME_ORB);
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
    void failsForGiratinaWithGriseousOrb() {
        user.setPokedexEntry(Pokedex.get("Giratina"));
        user.setHeldItem(GriseousOrb.GRISEOUS_ORB);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsForArceusWithPlate() {
        user.setPokedexEntry(Pokedex.get("Arceus"));
        user.setHeldItem(SPLASH_PLATE);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsForGenesectWithDrive() {
        user.setPokedexEntry(Pokedex.get("Genesect"));
        user.setHeldItem(SHOCK_DRIVE);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsForSilvallyWithMemory() {
        user.setPokedexEntry(Pokedex.get("Silvally"));
        user.setHeldItem(MemoryItem.DARK_MEMORY);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsForZacianWithRustedSword() {
        user.setPokedexEntry(Pokedex.get("Zacian"));
        user.setHeldItem(RUSTED_SWORD);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsForZamazentaWithRustedShield() {
        user.setPokedexEntry(Pokedex.get("Zamazenta"));
        user.setHeldItem(RUSTED_SHIELD);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void failsForMegaStoneOnCorrespondingPokemon() {
        user.setPokedexEntry(Pokedex.get("Tyranitar"));
        user.setHeldItem(MegaStone.TYRANITARITE);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void testFlingingBerryMakesOpponentConsumeBerry() {
        user.setHeldItem(ORAN_BERRY);

        final int damage = fling.calculateDamage(user, target, battle);
        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(Math.min((target.getMaxHP() - damage) + 10, target.getMaxHP()));
        assertThat(target.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);
        //TODO: The item should count as "consumed" by whom? The user or the target?
    }

    @Test
    void testUserOfUnnerveFlingsBerryAndTargetDoesNotConsumeIt() {
        user.setHeldItem(ORAN_BERRY);
        user.setAbility(UNNERVE);

        final int damage = fling.calculateDamage(user, target, battle);
        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP() - damage);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);
        //TODO: Is this right? Should the berry be counted as the flinger's consumed item?
    }

    @Test
    void testBlackGlassesBoostsAttackBeforeBeingLost() {
        assertThat(BLACK_GLASSES.getFlingPower()).isEqualTo(LOADED_DICE.getFlingPower());

        user.setHeldItem(LOADED_DICE);
        fling.executeOnTarget(user, target, battle);
        int unboostedDamage = target.getMaxHP() - target.getCurrentHP();
        target.fullRestore();

        user.setHeldItem(BLACK_GLASSES);
        fling.executeOnTarget(user, target, battle);
        int boostedDamage = target.getMaxHP() - target.getCurrentHP();

        assertThat(boostedDamage).isGreaterThan(unboostedDamage);
    }

    @Test
    void failsForGems() {
        user.setHeldItem(DARK_GEM);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void testLifeOrbBoostsPowerOfFlingButDealsNoRecoilDamage() {
        assertThat(LOADED_DICE.getFlingPower()).isEqualTo(LIFE_ORB.getFlingPower());
        user.setHeldItem(LOADED_DICE);
        fling.executeOnTarget(user, target, battle);
        int unboostedDamage = target.getMaxHP() - target.getCurrentHP();
        target.fullRestore();

        user.setHeldItem(LIFE_ORB);
        fling.executeOnTarget(user, target, battle);
        int boostedDamage = target.getMaxHP() - target.getCurrentHP();

        assertThat(boostedDamage).isGreaterThan(unboostedDamage);
        assertThat(user.getMaxHP()).isEqualTo(user.getCurrentHP());
    }

    @Test
    void testFlameOrbBurnsTarget() {
        user.setHeldItem(FLAME_ORB);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.getStatus()).isEqualTo(BURN);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(FLAME_ORB);
    }

    @Test
    void testToxicOrbBadlyPoisonsTarget() {
        user.setHeldItem(TOXIC_ORB);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.getStatus()).isEqualTo(TOXIC_POISON);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(TOXIC_ORB);
    }

    @Test
    void testKingsRockMakesTargetFlinch() {
        user.setHeldItem(KINGS_ROCK);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.hasVolatileStatus(VolatileStatusType.FLINCH)).isTrue();
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(KINGS_ROCK);
    }

    @Test
    void testLightBallParalyzesTarget() {
        user.setHeldItem(LIGHT_BALL);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.getStatus()).isEqualTo(PARALYZE);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(LIGHT_BALL);
    }

    @Test
    void testRazorFangMakesTargetFlinch() {
        user.setHeldItem(RAZOR_FANG);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.hasVolatileStatus(VolatileStatusType.FLINCH)).isTrue();
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(RAZOR_FANG);
    }

    @Test
    void testPoisonBarbPoisonsTarget() {
        user.setHeldItem(POISON_BARB);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.getStatus()).isEqualTo(Status.POISON);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(POISON_BARB);
    }

    @Test
    void testMentalHerbActivatesForTarget() {
        target.addVolatileStatus(VolatileStatus.INFATUATED);
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.TAUNTED, 5));
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.ENCORED, 5));
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.TORMENTED, 5));
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.DISABLED, 5));
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.HEAL_BLOCKED, 5));

        user.setHeldItem(MENTAL_HERB);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.hasVolatileStatus(VolatileStatusType.INFATUATED)).isFalse();
        assertThat(target.hasVolatileStatus(VolatileStatusType.TAUNTED)).isFalse();
        assertThat(target.hasVolatileStatus(VolatileStatusType.ENCORED)).isFalse();
        assertThat(target.hasVolatileStatus(VolatileStatusType.TORMENTED)).isFalse();
        assertThat(target.hasVolatileStatus(VolatileStatusType.DISABLED)).isFalse();
        assertThat(target.hasVolatileStatus(VolatileStatusType.HEAL_BLOCKED)).isFalse();
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(MENTAL_HERB);
    }

    @Test
    void testWhiteHerbActivatesForTarget() {
        target.setStageAttack(4);
        target.setStageDefense(-1);
        target.setStageSpecialDefense(0);
        target.setStageSpeed(-2);

        user.setHeldItem(WhiteHerb.WHITE_HERB);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.getStageAttack()).isEqualTo(4);
        assertThat(target.getStageDefense()).isZero();
        assertThat(target.getStageSpecialDefense()).isZero();
        assertThat(target.getStageSpeed()).isZero();
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(WhiteHerb.WHITE_HERB);
        //TODO: Same question as with berries -- does this count as the consumed item for the user or the target?
    }

    @Test
    void testSitrusBerryFlungAtTargetHoldingSitrusBerry() {
        user.setHeldItem(SITRUS_BERRY);
        target.setHeldItem(SITRUS_BERRY);
        target.setCurrentHP((target.getMaxHP() / 2) + 1);

        final int damage = fling.calculateDamage(user, target, battle);
        fling.executeOnTarget(user, target, battle);

        //TODO: Keep this one if the target eats its own held berry before the flung berry
        assertThat(target.getCurrentHP()).isEqualTo(((target.getMaxHP() / 2) + (1 - damage)) + target.getMaxHP() / 2);
        //TODO: Keep this one if the target eats the flung berry before its own held berry.
        assertThat(target.getCurrentHP()).isEqualTo(((target.getMaxHP() / 2) + (1 - damage)) + target.getMaxHP() / 4);
    }

    @Test
    void testBerryActivatesWithoutUsualTriggerCondition() {
        user.setHeldItem(SITRUS_BERRY);

        final int damage = fling.calculateDamage(user, target, battle);
        assertThat(user.getMaxHP() - damage).isGreaterThan(user.getMaxHP() / 2);
        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(Math.min(target.getMaxHP(),
            (target.getMaxHP() - damage) + target.getMaxHP() / 2));
        assertThat(target.getConsumedItem()).isEqualTo(SITRUS_BERRY);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);
    }

    @Test
    void testItemNotConsumedIfAttackMisses() {
        Main.HIT_RANDOM = MAX_RANDOM;
        user.setHeldItem(LEFTOVERS);
        fling.executeOnTarget(user, target, battle);

        assertThat(user.getHeldItem()).isEqualTo(LEFTOVERS);
        assertThat(user.getConsumedItem()).isEqualTo(NO_ITEM);
        //TODO: Should the item be consumed if the attack misses?
        assertThat(false).isTrue();
    }

    @Test
    void testUserConsumesItemAndNotTargetIfAttackHitsSubstitute() {
        target.addVolatileStatus(new SubstituteStatus(100));
        user.setHeldItem(FLAME_ORB);
        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
        assertThat(target.getStatus()).isEqualTo(NONE);
        assertThat(target.getConsumedItem()).isEqualTo(NO_ITEM);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(FLAME_ORB);
        //TODO: Is this what's supposed to happen?
        assertThat(false).isTrue();
    }
}
