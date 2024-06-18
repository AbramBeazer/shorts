package org.shorts.model.moves;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.battle.Weather;
import org.shorts.model.abilities.FullHealthHalfDamageAbility;
import org.shorts.model.abilities.SuperEffectiveReducingAbility;
import org.shorts.model.abilities.statpreserving.PreserveAccuracyIgnoreEvasionAbility;
import org.shorts.model.items.MetronomeItem;
import org.shorts.model.items.berries.typeresist.TypeResistBerry;
import org.shorts.model.moves.trapping.binding.Whirlpool;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.PumpedStatus;
import org.shorts.model.status.Status;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.TooManyTypesException;
import org.shorts.model.types.Type;

import static org.shorts.Main.RANDOM;
import static org.shorts.MathUtils.roundHalfDown;
import static org.shorts.MathUtils.roundHalfUp;
import static org.shorts.model.abilities.BattleArmor.BATTLE_ARMOR;
import static org.shorts.model.abilities.CompoundEyes.COMPOUND_EYES;
import static org.shorts.model.abilities.Fluffy.FLUFFY;
import static org.shorts.model.abilities.GaleWings.GALE_WINGS;
import static org.shorts.model.abilities.Guts.GUTS;
import static org.shorts.model.abilities.Hustle.HUSTLE;
import static org.shorts.model.abilities.IceScales.ICE_SCALES;
import static org.shorts.model.abilities.Infiltrator.INFILTRATOR;
import static org.shorts.model.abilities.Merciless.MERCILESS;
import static org.shorts.model.abilities.Neuroforce.NEUROFORCE;
import static org.shorts.model.abilities.Prankster.PRANKSTER;
import static org.shorts.model.abilities.Pressure.PRESSURE;
import static org.shorts.model.abilities.PunkRock.PUNK_ROCK;
import static org.shorts.model.abilities.Ripen.RIPEN;
import static org.shorts.model.abilities.SandVeil.SAND_VEIL;
import static org.shorts.model.abilities.Scrappy.SCRAPPY;
import static org.shorts.model.abilities.SereneGrace.SERENE_GRACE;
import static org.shorts.model.abilities.SheerForce.SHEER_FORCE;
import static org.shorts.model.abilities.ShellArmor.SHELL_ARMOR;
import static org.shorts.model.abilities.SkillLink.SKILL_LINK;
import static org.shorts.model.abilities.Sniper.SNIPER;
import static org.shorts.model.abilities.SnowCloak.SNOW_CLOAK;
import static org.shorts.model.abilities.SuperLuck.SUPER_LUCK;
import static org.shorts.model.abilities.TangledFeet.TANGLED_FEET;
import static org.shorts.model.abilities.ThickFat.THICK_FAT;
import static org.shorts.model.abilities.TintedLens.TINTED_LENS;
import static org.shorts.model.abilities.Triage.TRIAGE;
import static org.shorts.model.items.BrightPowder.BRIGHT_POWDER;
import static org.shorts.model.items.ExpertBelt.EXPERT_BELT;
import static org.shorts.model.items.IronBall.IRON_BALL;
import static org.shorts.model.items.LaxIncense.LAX_INCENSE;
import static org.shorts.model.items.Leek.LEEK;
import static org.shorts.model.items.LifeOrb.LIFE_ORB;
import static org.shorts.model.items.LoadedDice.LOADED_DICE;
import static org.shorts.model.items.LuckyPunch.LUCKY_PUNCH;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.RazorClaw.RAZOR_CLAW;
import static org.shorts.model.items.RingTarget.RING_TARGET;
import static org.shorts.model.items.ScopeLens.SCOPE_LENS;
import static org.shorts.model.items.WideLens.WIDE_LENS;
import static org.shorts.model.items.ZoomLens.ZOOM_LENS;
import static org.shorts.model.status.VolatileStatusType.ABILITY_IGNORED;
import static org.shorts.model.status.VolatileStatusType.ABILITY_SUPPRESSED;
import static org.shorts.model.status.VolatileStatusType.CONFUSED;
import static org.shorts.model.status.VolatileStatusType.IDENTIFIED;
import static org.shorts.model.status.VolatileStatusType.LASER_FOCUS;
import static org.shorts.model.status.VolatileStatusType.MAGIC_COAT;
import static org.shorts.model.status.VolatileStatusType.MICLE_BERRY_EFFECT;
import static org.shorts.model.status.VolatileStatusType.MINIMIZED;
import static org.shorts.model.status.VolatileStatusType.PUMPED;
import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;
import static org.shorts.model.status.VolatileStatusType.TARRED;
import static org.shorts.model.types.Type.FIGHTING;
import static org.shorts.model.types.Type.FIRE;
import static org.shorts.model.types.Type.FLYING;
import static org.shorts.model.types.Type.GHOST;
import static org.shorts.model.types.Type.GROUND;
import static org.shorts.model.types.Type.ICE;
import static org.shorts.model.types.Type.IMMUNE;
import static org.shorts.model.types.Type.NEUTRAL;
import static org.shorts.model.types.Type.NORMAL;
import static org.shorts.model.types.Type.SUPER_EFFECTIVE;
import static org.shorts.model.types.Type.WATER;

public abstract class Move {

    public enum Category {
        PHYSICAL,
        SPECIAL,
        STATUS
    }

    private final String name;
    private final double power;
    private final double accuracy;
    private final Type type;
    private final Category category;
    private final Range range;
    private int currentPP;
    private final int maxPP;

    private final boolean contact;

    private final int secondaryEffectChance;
    private boolean disabled = false;

    protected Move(
        String name,
        double power,
        double accuracy,
        Type type,
        Category category,
        Range range,
        int maxPP,
        boolean contact,
        int secondaryEffectChance) {
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.type = type;
        this.category = category;
        this.range = range;
        this.maxPP = maxPP;
        this.currentPP = maxPP;
        this.contact = contact;
        this.secondaryEffectChance = secondaryEffectChance;
    }

    public String getName() {
        return this.name;
    }

    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        return this.power;
    }

    public double getAccuracy() {
        return this.accuracy;
    }

    public Type getType() {
        return this.type;
    }

    public Category getCategory() {
        return category;
    }

    public Range getRange(Pokemon user) {
        return range;
    }

    public int getCurrentPP() {
        return this.currentPP;
    }

    public int getMaxPP() {
        return this.maxPP;
    }

    public boolean isContact() {
        return contact;
    }

    public int getPriority(Pokemon attacker, Battle battle) {
        return 0;
    }

    public int getAbilityPriorityBonus(Pokemon user) {
        if (user.hasVolatileStatus(ABILITY_SUPPRESSED)) {
            return 0;
        } else if (user.getAbility() == PRANKSTER && this.category == Category.STATUS) {
            return 1;
        } else if (user.getAbility() == GALE_WINGS && user.isAtFullHP() && this.type == FLYING) {
            return 1;
        } else if (user.getAbility() == TRIAGE && this instanceof HealingMove) {
            return 3;
        } else {
            return 0;
        }
    }

    public int getSecondaryEffectChance() {
        return secondaryEffectChance;
    }

    public int getNumHits(boolean skillLink, boolean loadedDice) {
        return 1;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (category == Category.STATUS || (user.getAbility() != SHEER_FORCE && secondaryEffectChance > 0)) {

            final int chance = getSecondaryEffectChance() * (user.getAbility().equals(SERENE_GRACE) ? 2 : 1);
            if (RANDOM.nextInt(100) < chance) {
                applySecondaryEffect(user, target, battle);
            }
        }
    }

    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
    }

    protected void onStartup(Pokemon user) {
    }

    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        if (accuracy <= 0) {
            return true;
        }
        //TODO: Implement semi-invulnerable
        int threshold = (int) roundHalfDown(
            getModifiedAccuracy(user, target, battle) * getAccuracyEvasionStageModifier(user, target)
                * (user.hasVolatileStatus(MICLE_BERRY_EFFECT) ? 1.2 : 1));
        return RANDOM.nextInt(100) < threshold;
    }

    private double getModifiedAccuracy(Pokemon user, Pokemon target, Battle battle) {
        final double divisor = 4096d;
        double mod = divisor;
        if (battle.getGravityTurns() > 0) {
            mod = roundHalfUp(mod * (6840 / divisor));
        }
        if (target.getAbility() == TANGLED_FEET && !target.hasVolatileStatus(ABILITY_IGNORED)
            && !target.hasVolatileStatus(ABILITY_SUPPRESSED) && target.hasVolatileStatus(CONFUSED)) {
            mod = roundHalfUp(mod * 0.5);
        }
        if (target.getAbility() == HUSTLE && this.category == Category.PHYSICAL) {
            mod = roundHalfUp(mod * (3277 / divisor));
        }
        if (!battle.isWeatherSuppressed() && !target.hasVolatileStatus(ABILITY_SUPPRESSED) && !target.hasVolatileStatus(
            ABILITY_IGNORED) && ((target.getAbility() == SAND_VEIL && battle.getWeather() == Weather.SAND) || (
            target.getAbility() == SNOW_CLOAK && (battle.getWeather() == Weather.HAIL
                || battle.getWeather() == Weather.SNOW)))) {
            mod = roundHalfUp(mod * (3277 / divisor));
        }
        int countVictoryStarBoosts = battle.getNumberOfActivePokemonWithVictoryStar(battle.getCorrespondingTrainer(user));
        if (countVictoryStarBoosts > 0) {
            mod = roundHalfUp(mod * Math.pow(4506 / divisor, countVictoryStarBoosts));
        }
        if (user.getAbility() == COMPOUND_EYES) {
            mod = roundHalfUp(mod * (5325 / divisor));
        }
        if (target.getHeldItem() == BRIGHT_POWDER || target.getHeldItem() == LAX_INCENSE) {
            mod = roundHalfUp(mod * (3686 / divisor));
        }
        if (user.getHeldItem() == WIDE_LENS) {
            mod = roundHalfUp(mod * (4505 / divisor));
        }
        if (user.getHeldItem() == ZOOM_LENS && target.hasMovedThisTurn()) {
            mod = roundHalfUp(mod * (4915 / divisor));
        }

        return roundHalfDown((this.accuracy * mod) / divisor);
    }

    protected double getAccuracyEvasionStageModifier(Pokemon user, Pokemon target) {
        int evasionStage = target.getStageEvasion();
        if (evasionStage > 0 && (target.hasVolatileStatus(IDENTIFIED)
            || (user.getAbility() instanceof PreserveAccuracyIgnoreEvasionAbility && !user.hasVolatileStatus(
            ABILITY_SUPPRESSED)))) {
            evasionStage = 0;
        }
        int combinedStage = Math.max(-6, Math.min(user.getStageAccuracy() - evasionStage, 6));
        if (combinedStage < 0) {
            return 3d / Math.abs(combinedStage - 3);
        } else {
            return (combinedStage + 3) / 3d;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Move)) {
            return false;
        }
        Move other = (Move) Objects.requireNonNull(o);
        return this.name.equals(other.name) && this.power == other.power && this.accuracy == other.accuracy
            && this.type.equals(other.type) && this.maxPP == other.maxPP && this.contact == other.contact
            && this.secondaryEffectChance == other.secondaryEffectChance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, power, accuracy, type, maxPP, contact, secondaryEffectChance);
    }

    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        user.setMovedThisTurn(true);
        this.decrementPP();
        this.onStartup(user);
        for (Pokemon target : targets) {

            //TODO: What if a random-target move targets a Pokemon that has fainted and hasn't been switched out yet?
            //TODO: What if any move targets a Pokemon that's fainted? Does it just hit another available adjacent enemy?

            //TODO: Move this Pressure logic to whatever method calls this one. Pressure shouldn't activate for Curse or Sticky Web but should activate for moves that target the whole field, like Rain Dance. I think it should only activate once in the case of Rain Dance, even if multiple opponents have it.
            //  Should it affect moves that affect the enemy side? It affects all hazard moves except Sticky Web.
            //  If a Pokémon uses Tera Blast while one of its opponents has Pressure, the additional PP will be deducted even if the Pressure Pokémon is not the move's target.
            //  Pressure increases the PP consumption of an opponent's Imprison and Snatch even though those are self-targeting moves; in Snatch's case the additional PP is consumed even if Snatch fails or snatches a move from a Pokémon other than the one with Pressure.
            if (battle.getCorrespondingTrainer(user) != battle.getCorrespondingTrainer(target)
                && target.getAbility().equals(PRESSURE) && this.getCurrentPP() > 0 && pressureApplies(
                user,
                target)) {
                this.decrementPP();
            }

            if (this.category == Category.STATUS && user != target && target.hasVolatileStatus(MAGIC_COAT)) {
                target = user;
            }

            executeOnTarget(user, target, battle);
            user.setLastMoveUsed(this);
            //if(userMon.getCurrentHP() == 0) {
            //  //TODO: Handle fainting and subsequent switch-in.
            //}

            //TODO: Handle Endure, Destiny Bond, Perish Song, etc.
            if (target.getCurrentHP() == 0) {
                //Or should I have this call in Pokemon.takeDamage()?
                target.afterFaint(user, battle);
                user.afterKO(target, battle);
                //TODO: Handle fainting and subsequent switch-in.

            }
        }
    }

    protected void executeOnTarget(Pokemon user, Pokemon target, Battle battle) {
        if (rollToHit(user, target, battle)) {
            if (this.category == Category.STATUS) {
                //TODO: This may change -- Will-O-Wisp shouldn't burn Flash Fire mons, Thunder Wave won't affect ground-types, and poison moves won't affect steels, but I think other status moves might ignore types.
                if (target.beforeHit(user, battle, this) > 0 && getTypeMultiplier(user, target, battle) > 0) {
                    this.trySecondaryEffect(user, target, battle);
                }
            } else {

                int hitNum = 0;
                final int maxHits = this.getNumHits(user.getAbility() == SKILL_LINK, user.getHeldItem() == LOADED_DICE);
                while (hitNum < maxHits && !user.hasFainted() && !target.hasFainted()) {
                    final int previousTargetHP = target.getCurrentHP();

                    int damage = calculateDamage(user, target, battle);
                    if (target.hasVolatileStatus(SUBSTITUTE)) { //TODO: Handle moves and abilities that ignore substitute.
                        ((SubstituteStatus) target.getVolatileStatus(SUBSTITUTE)).takeDamage(damage);
                    } else {
                        target.takeDamage(damage);
                    }

                    if (!user.hasFainted()) {
                        this.inflictRecoil(user, damage);
                    }

                    //TODO: Verify which effects should happen after the attack hits the sub and which shouldn't.
                    if (!target.hasVolatileStatus(SUBSTITUTE)) {
                        target.afterHit(user, battle, previousTargetHP, this);
                    }

                    this.trySecondaryEffect(user, target, battle);
                    if (target.hasVolatileStatus(SUBSTITUTE)
                        && ((SubstituteStatus) target.getVolatileStatus(SUBSTITUTE)).getSubHP() == 0) {
                        target.removeVolatileStatus(SUBSTITUTE);
                    }

                    hitNum++;
                }
                if (hitNum > 1) {
                    System.out.println("Hit " + hitNum + " times!");
                }

                if (!user.hasFainted()) {
                    user.afterAttack(target, battle, this);
                }
            }
        }
    }

    protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
        double movePower = calculateMovePower(user, target, battle);
        double userAttackMultipliers = user.getAttackMultipliersFromAbilityAndItem(target, battle, this);

        //TODO: Critical hits should ignore attack drops and defense buffs.
        double attackingStat = getAttackingStat(user, target) * userAttackMultipliers;
        double defendingStat = getDefendingStat(user, target, battle);
        //TODO: Deal with multi-hit moves and the weirdness that is Beat Up.

        double baseDamage = ((0.4 * user.getLevel() + 2) * movePower * (attackingStat / defendingStat) * 0.02) + 2;
        return applyMultipliers(user, target, battle, baseDamage);
    }

    private int applyMultipliers(Pokemon user, Pokemon target, Battle battle, double baseDamage) {
        boolean isCritical = rollForCrit(user, target, battle);
        double typeMultiplier = this.getTypeMultiplier(user, target, battle);

        if (typeMultiplier == IMMUNE) {
            //TODO: LOGGER.info("It didn't affect {}", target.getLead().getNickname());
            return 0;
        }

        double stabMultiplier = getSTABMultiplier(user.getTypes());

        baseDamage = roundHalfDown(baseDamage * getNumTargetsMultiplier());
        if (!battle.isWeatherSuppressed()) {
            baseDamage = roundHalfDown(baseDamage * getWeatherMultiplier(battle));
        }
        baseDamage = roundHalfDown(baseDamage * getGlaiveRushMultiplier(target));
        baseDamage = roundHalfDown(baseDamage * getCriticalMultiplier(isCritical));
        baseDamage = roundHalfDown(baseDamage * getRandomMultiplier());
        baseDamage = roundHalfDown(baseDamage * stabMultiplier);
        baseDamage = roundHalfDown(baseDamage * typeMultiplier);
        baseDamage = roundHalfDown(baseDamage * getOtherMultiplier(user, target, battle, isCritical, typeMultiplier));
        return (int) baseDamage;
    }

    protected double getTypeMultiplier(Pokemon user, Pokemon target, Battle battle) {
        double multiplier = getBaseTypeMultiplier(target.getTypes());
        multiplier *= target.beforeHit(user, battle, this);

        if (!target.isGrounded() && target.getTypes().contains(FLYING) && (target.getHeldItem() == IRON_BALL)) {
            multiplier = 1;
        } else if (target.isGrounded() && target.getTypes().contains(FLYING) && this.type == GROUND && (
            target.getHeldItem() != IRON_BALL)) {
            multiplier = getBaseTypeMultiplier(target.getTypes()
                .stream()
                .filter(t -> t != FLYING)
                .collect(Collectors.toSet()));
        } else if (multiplier == IMMUNE && (target.getHeldItem() == RING_TARGET || target.hasVolatileStatus(
            VolatileStatusType.IDENTIFIED))) {
            multiplier = getBaseTypeMultiplier(target.getTypes()
                .stream()
                .filter(t -> !t.getImmunities().contains(this.type.getId()))
                .collect(Collectors.toSet()));
        } else if (user.getAbility() == SCRAPPY && (this.type == NORMAL || this.type == FIGHTING) && target.getTypes()
            .contains(GHOST)) {
            multiplier = getBaseTypeMultiplier(target.getTypes()
                .stream()
                .filter(t -> t != GHOST)
                .collect(Collectors.toSet()));
        } else if (battle.getWeather() == Weather.EXTREME_WIND && !battle.isWeatherSuppressed()
            && getBaseTypeMultiplier(Set.of(FLYING)) >= SUPER_EFFECTIVE) {
            multiplier = getBaseTypeMultiplier(target.getTypes()
                .stream()
                .filter(t -> t != FLYING)
                .collect(Collectors.toSet()));
        }
        //TODO: Take a closer look at how Ring Target and Foresight work. Do they affect Levitate, or just the type chart itself?
        if (target.hasVolatileStatus(TARRED) && this.type == FIRE) {
            multiplier *= 2;
        }
        return multiplier;
    }

    protected double getBaseTypeMultiplier(Set<Type> defenderTypes) throws TooManyTypesException {
        //TODO: Deal with Terrastalization and stuff.
        return Type.getTypeMultiplier(this.getType(), defenderTypes);
    }

    protected double getSTABMultiplier(Set<Type> attackerTypes) throws TooManyTypesException {
        //TODO: Deal with Terrastalization and stuff.
        return Type.getSTABMultiplier(this.getType(), attackerTypes);
    }

    protected double calculateMovePower(Pokemon user, Pokemon target, Battle battle) {
        double basePower = this.getPower(user, target, battle) * this.getPowerMultipliers(user, target, battle);
        basePower *= user.getMovePowerMultipliers(target, battle, this);
        //TODO: Handle weather multipliers, terrain multipliers, mud sport, etc.
        //TODO: Investigate what, if anything, I need to do on the target's side of things.
        return basePower;
    }

    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        return 1;
    }

    private double getRandomMultiplier() {
        return (RANDOM.nextInt(16) + 85) / 100d;
    }

    private double getNumTargetsMultiplier() {
        return 1;
    }

    protected double getWeatherMultiplier(Battle battle) {
        if (battle.getWeather() == Weather.RAIN || battle.getWeather() == Weather.EXTREME_RAIN) {
            if (type == WATER) {
                return 1.5;
            } else {
                return type == FIRE ? 0.5 : 1;
            }
        } else if (battle.getWeather() == Weather.SUN || battle.getWeather() == Weather.EXTREME_SUN) {
            if (type == FIRE) {
                return 1.5;
            } else {
                return type == WATER ? 0.5 : 1;
            }

        }
        return 1;
    }

    private double getGlaiveRushMultiplier(Pokemon target) {
        return target.hasVolatileStatus(VolatileStatusType.USED_GLAIVE_RUSH) ? 2 : 1;
    }

    private boolean rollForCrit(Pokemon user, Pokemon target, Battle battle) {
        if (((target.getAbility() == BATTLE_ARMOR || target.getAbility() == SHELL_ARMOR) && !target.hasVolatileStatus(
            ABILITY_SUPPRESSED) && !target.hasVolatileStatus(ABILITY_IGNORED))
            || battle.getCorrespondingTrainer(target).getLuckyChantTurns() > 0) {
            return false;
        } else if (user.getAbility() == MERCILESS && (target.getStatus() == Status.POISON
            || target.getStatus() == Status.TOXIC_POISON)) {
            return true;
        } else if (user.hasVolatileStatus(LASER_FOCUS) || this instanceof AlwaysCritMove) {
            return true;
        } else {
            int stage = 0;
            if (this instanceof HighCritChanceMove) {
                stage++;
            }
            if (user.getAbility() == SUPER_LUCK) {
                stage++;
            }
            if (user.getHeldItem() == RAZOR_CLAW || user.getHeldItem() == SCOPE_LENS) {
                stage++;
            } else if ((user.getHeldItem() == LUCKY_PUNCH && user.getPokedexEntry().getSpeciesName().equals("Chansey"))
                || (user.getHeldItem() == LEEK && Set.of("Farfetch'd", "Farfetch'd-G", "Sirfetch'd")
                .contains(user.getPokedexEntry().getSpeciesName()))) {
                stage += 2;
            }
            if (user.hasVolatileStatus(PUMPED)) {
                stage += ((PumpedStatus) user.getVolatileStatus(PUMPED)).getLevels();
            }

            final int rand = RANDOM.nextInt(24);
            if (stage <= 0) {
                return rand < 1;
            } else if (stage == 1) {
                return rand < 3;
            } else if (stage == 2) {
                return rand < 12;
            } else {
                return true;
            }
        }
    }

    private double getCriticalMultiplier(boolean isCritical) {
        return isCritical ? 1.5 : 1;
    }

    protected double getBurnMultiplier(Pokemon user) {
        if (this.category == Category.PHYSICAL && user.getStatus() == Status.BURN && !user.getAbility().equals(GUTS)) {
            return 0.5;
        } else {
            return 1;
        }
    }

    private double getOtherMultiplier(
        Pokemon user, Pokemon target, Battle battle, boolean critical, double typeMultiplier) {
        final double divisor = 4096d;
        double base = divisor;
        if (this instanceof HitsMinimize && target.hasVolatileStatus(MINIMIZED)) {
            base = roundHalfUp(base * 2);
        }
        if ((this instanceof Earthquake || this instanceof Magnitude) && (target.hasVolatileStatus(SEMI_INVULNERABLE)
            && target.getVolatileStatus(SEMI_INVULNERABLE).getMove() instanceof Dig)) {
            base = roundHalfUp(base * 2);
        }
        if ((this instanceof Surf || this instanceof Whirlpool) && (target.hasVolatileStatus(SEMI_INVULNERABLE)
            && target.getVolatileStatus(SEMI_INVULNERABLE).getMove() instanceof Dive)) {
            base = roundHalfUp(base * 2);
        }

        if (!critical && user.getAbility() != INFILTRATOR) {
            Trainer opposingTrainer = battle.getOpposingTrainer(user);
            if (this.category == Category.PHYSICAL && (opposingTrainer.getReflectTurns() > 0
                || opposingTrainer.getAuroraVeilTurns() > 0)) {
                base = roundHalfUp(base * 0.5);
            } else if (this.category == Category.SPECIAL && (opposingTrainer.getLightScreenTurns() > 0
                || opposingTrainer.getAuroraVeilTurns() > 0)) {
                base = roundHalfUp(base * 0.5);
            }
        }

        if (this instanceof ExtraSuperEffectiveDamageAttack && typeMultiplier > NEUTRAL) {
            base = roundHalfUp(base * 5461d / divisor);
        }

        if (target.getCurrentHP() == target.getMaxHP() && target.getAbility() instanceof FullHealthHalfDamageAbility) {
            base = roundHalfUp(base * 0.5);
        }

        if (target.getAbility() == FLUFFY && this.isContact()) {
            base = roundHalfUp(base * 0.5);
        }

        if (target.getAbility() == PUNK_ROCK && this instanceof SoundEffect) {
            base = roundHalfUp(base * 0.5);
        }

        if (target.getAbility() == ICE_SCALES && this.category == Category.SPECIAL) {
            base = roundHalfUp(base * 0.5);
        }

        //Friend guard would go here, in double/triple battles.

        if (typeMultiplier > NEUTRAL && target.getAbility() instanceof SuperEffectiveReducingAbility) {
            base = roundHalfUp(base * 0.75);
        }
        if (typeMultiplier > NEUTRAL && user.getAbility() == NEUROFORCE) {
            base = roundHalfUp(base * 1.25);
        }
        if (critical && user.getAbility() == SNIPER) {
            base = roundHalfUp(base * 1.5);
        }
        if (typeMultiplier < NEUTRAL && user.getAbility() == TINTED_LENS) {
            base = roundHalfUp(base * 2);
        }
        if (type == FIRE && target.getAbility() == FLUFFY) {
            base = roundHalfUp(base * 2);
        }
        if (target.getHeldItem() instanceof TypeResistBerry) {
            TypeResistBerry berry = (TypeResistBerry) target.getHeldItem();
            if (berry.getType() == this.type && (this.type == NORMAL || typeMultiplier > NEUTRAL)) {
                double multiplier = target.getAbility() == RIPEN ? 0.25 : 0.5;
                base = roundHalfUp(base * multiplier);
            }
            //TODO: Output berry-eating message
            target.setHeldItem(NO_ITEM);
        }
        if (user.getHeldItem() == EXPERT_BELT && typeMultiplier > NEUTRAL) {
            base = roundHalfUp(base * 4915d / divisor);
        }
        if (user.getHeldItem() == LIFE_ORB) {
            base = roundHalfUp(base * 5324d / divisor);
        }
        if (user.getHeldItem() instanceof MetronomeItem) {
            MetronomeItem metronome = (MetronomeItem) user.getHeldItem();
            double metronomeMultiplier = 1 + (metronome.getPreviousUses() * 819d / divisor);
            base = roundHalfUp(base * metronomeMultiplier);
        }
        return base / divisor;
    }

    private void decrementPP() {
        if (this.currentPP > 0) {
            this.currentPP--;
        }
    }

    protected boolean pressureApplies(Pokemon userMon, Pokemon targetMon) {
        return userMon != targetMon;
    }

    protected double getAttackingStat(Pokemon attacker, Pokemon defender) {
        //WHY does Thick Fat apply to the attack calculation instead of just halving the damage like Heatproof does? This makes no sense! Why, GameFreak? Why?
        final double applyThickFat = defender.getAbility() == THICK_FAT && (this.type == ICE || this.type == FIRE)
            && !defender.hasVolatileStatus(ABILITY_IGNORED) && !defender.hasVolatileStatus(ABILITY_SUPPRESSED)
            ? 0.5
            : 1;
        if (category == Category.PHYSICAL) {
            return attacker.calculateAttack() * applyThickFat;
        } else if (category == Category.SPECIAL) {
            return attacker.calculateSpecialAttack() * applyThickFat;
        } else {
            return 0;
        }
    }

    protected double getDefendingStat(Pokemon attacker, Pokemon defender, Battle battle) {
        if (category == Category.PHYSICAL) {
            return defender.calculateDefense();
        } else if (category == Category.SPECIAL) {
            return defender.calculateSpecialDefense(battle);
        } else {
            return 0;
        }
    }

    protected void inflictRecoil(Pokemon user, int damageDealt) {
    }

}
