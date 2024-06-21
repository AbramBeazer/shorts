package org.shorts.model.moves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.MockRandomReturnZero;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
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
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.Klutz.KLUTZ;
import static org.shorts.model.abilities.Unnerve.UNNERVE;
import static org.shorts.model.items.BoosterEnergy.BOOSTER_ENERGY;
import static org.shorts.model.items.DriveItem.SHOCK_DRIVE;
import static org.shorts.model.items.FlameOrb.FLAME_ORB;
import static org.shorts.model.items.Gem.DARK_GEM;
import static org.shorts.model.items.KingsRock.KINGS_ROCK;
import static org.shorts.model.items.Leftovers.LEFTOVERS;
import static org.shorts.model.items.LightBall.LIGHT_BALL;
import static org.shorts.model.items.MentalHerb.MENTAL_HERB;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.PlateItem.SPLASH_PLATE;
import static org.shorts.model.items.PoisonBarb.POISON_BARB;
import static org.shorts.model.items.PrimalOrb.RED_ORB;
import static org.shorts.model.items.RazorFang.RAZOR_FANG;
import static org.shorts.model.items.RustedShield.RUSTED_SHIELD;
import static org.shorts.model.items.RustedSword.RUSTED_SWORD;
import static org.shorts.model.items.ToxicOrb.TOXIC_ORB;
import static org.shorts.model.items.berries.OranBerry.ORAN_BERRY;
import static org.shorts.model.status.Status.BURN;
import static org.shorts.model.status.Status.PARALYZE;
import static org.shorts.model.status.Status.TOXIC_POISON;

class FlingTests {

    private Fling fling;
    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() throws Exception {
        Pokedex.create();
        fling = new Fling();
        user = PokemonTestUtils.getDummyPokemon();
        target = PokemonTestUtils.getDummyPokemon();
        battle = new DummySingleBattle(user, target);
        Main.RANDOM = new MockRandomReturnZero();
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
        user.setHeldItem(LEFTOVERS);

        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.PROTECTED, 1));

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(LEFTOVERS);
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
    void testFlingingTRHasPowerEqualToPowerOfMoveInTR() {
        assertThat(false).isTrue();
    }

    @Test
    void testFlingingBerryMakesOpponentConsumeBerry() {
        user.setHeldItem(ORAN_BERRY);

        final int damage = fling.calculateDamage(user, target, battle);
        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo((target.getMaxHP() - damage) + 10);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        //TODO: The item should count as "consumed" by whom? The user or the target?
    }

    @Test
    void testUserOfUnnerveFlingsBerryAndTargetDoesNotConsumeIt() {
        user.setHeldItem(ORAN_BERRY);
        user.setAbility(UNNERVE);

        final int damage = fling.calculateDamage(user, target, battle);
        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP() - damage);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(ORAN_BERRY);
        //TODO: Is this right? Should the berry be counted as the flinger's consumed item?
    }

    @Test
    void testBlackGlassesBoostsAttackBeforeBeingLost() {
        assertThat(false).isTrue();
    }

    @Test
    void failsForGems() {
        user.setHeldItem(DARK_GEM);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void testLifeOrbBoostsPowerOfFlingButDealsNoRecoilDamage() {
        assertThat(false).isTrue();
    }

    @Test
    void testFlameOrbBurnsTarget() {
        user.setHeldItem(FLAME_ORB);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.getStatus()).isEqualTo(BURN);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(FLAME_ORB);
    }

    @Test
    void testToxicOrbBadlyPoisonsTarget() {
        user.setHeldItem(TOXIC_ORB);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.getStatus()).isEqualTo(TOXIC_POISON);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(TOXIC_ORB);
    }

    @Test
    void testKingsRockMakesTargetFlinch() {
        user.setHeldItem(KINGS_ROCK);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.hasVolatileStatus(VolatileStatusType.FLINCH)).isTrue();
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(KINGS_ROCK);
    }

    @Test
    void testLightBallParalyzesTarget() {
        user.setHeldItem(LIGHT_BALL);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.getStatus()).isEqualTo(PARALYZE);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(LIGHT_BALL);
    }

    @Test
    void testRazorFangMakesTargetFlinch() {
        user.setHeldItem(RAZOR_FANG);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.hasVolatileStatus(VolatileStatusType.FLINCH)).isTrue();
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(RAZOR_FANG);
    }

    @Test
    void testPoisonBarbPoisonsTarget() {
        user.setHeldItem(POISON_BARB);

        fling.executeOnTarget(user, target, battle);

        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
        assertThat(target.getStatus()).isEqualTo(Status.POISON);
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
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(WhiteHerb.WHITE_HERB);
        //TODO: Same question as with berries -- does this count as the consumed item for the user or the target?
    }
}
