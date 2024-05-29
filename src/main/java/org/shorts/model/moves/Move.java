package org.shorts.model.moves;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.battle.Weather;
import org.shorts.model.abilities.FullHealthHalfDamageAbility;
import org.shorts.model.abilities.SuperEffectiveReducingAbility;
import org.shorts.model.items.MetronomeItem;
import org.shorts.model.items.berries.typeresist.TypeResistBerry;
import org.shorts.model.moves.trapping.binding.Whirlpool;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.TooManyTypesException;
import org.shorts.model.types.Type;

import static org.shorts.Main.RANDOM;
import static org.shorts.MathUtils.roundHalfDown;
import static org.shorts.MathUtils.roundHalfUp;
import static org.shorts.model.abilities.Fluffy.FLUFFY;
import static org.shorts.model.abilities.Guts.GUTS;
import static org.shorts.model.abilities.IceScales.ICE_SCALES;
import static org.shorts.model.abilities.Infiltrator.INFILTRATOR;
import static org.shorts.model.abilities.Neuroforce.NEUROFORCE;
import static org.shorts.model.abilities.Prankster.PRANKSTER;
import static org.shorts.model.abilities.Pressure.PRESSURE;
import static org.shorts.model.abilities.PunkRock.PUNK_ROCK;
import static org.shorts.model.abilities.Ripen.RIPEN;
import static org.shorts.model.abilities.Scrappy.SCRAPPY;
import static org.shorts.model.abilities.SereneGrace.SERENE_GRACE;
import static org.shorts.model.abilities.SheerForce.SHEER_FORCE;
import static org.shorts.model.abilities.SkillLink.SKILL_LINK;
import static org.shorts.model.abilities.Sniper.SNIPER;
import static org.shorts.model.abilities.TintedLens.TINTED_LENS;
import static org.shorts.model.items.ExpertBelt.EXPERT_BELT;
import static org.shorts.model.items.IronBall.IRON_BALL;
import static org.shorts.model.items.LifeOrb.LIFE_ORB;
import static org.shorts.model.items.LoadedDice.LOADED_DICE;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.RingTarget.RING_TARGET;
import static org.shorts.model.status.VolatileStatusType.MAGIC_COAT;
import static org.shorts.model.status.VolatileStatusType.MINIMIZED;
import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;
import static org.shorts.model.status.VolatileStatusType.TARRED;
import static org.shorts.model.types.Type.FIGHTING;
import static org.shorts.model.types.Type.FIRE;
import static org.shorts.model.types.Type.FLYING;
import static org.shorts.model.types.Type.GHOST;
import static org.shorts.model.types.Type.GROUND;
import static org.shorts.model.types.Type.IMMUNE;
import static org.shorts.model.types.Type.NEUTRAL;
import static org.shorts.model.types.Type.NORMAL;
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

    public double getPower() {
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

    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        // TODO:
        //  Dark-type Pokémon are now immune to opposing Pokémon's moves that gain priority due to Prankster, including moves called by moves that call other moves
        //  (such as Assist and Nature Power) and excluding moves that are repeated as a result of Prankster-affected Instruct
        //  or moves that occur earlier than their usual order due to Prankster-affected After You. Ally Dark-type Pokémon are still affected by the user's status moves.
        //  Dark-type Pokémon can still bounce moves back with Magic Bounce or Magic Coat; moves that have increased priority due to Prankster which are reflected
        //  by Magic Bounce or Magic Coat can affect Dark-type Pokémon, unless the Pokémon that bounced the move with Magic Coat also has Prankster.
        //  Moves that target all Pokémon (except Perish Song and Rototiller, which cannot affect Dark-type opponents if boosted by Prankster) and moves that set traps are successful regardless of the presence of Dark-type Pokémon.

        if (category == Category.STATUS && attacker.getAbility() == PRANKSTER) {
            return 1;
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

    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        if (accuracy <= 0) {
            return true;
        }
        //TODO: Apply accuracy and evasion.
        return RANDOM.nextInt(100) < accuracy;
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

    public void determineTargetAndExecuteMove(Pokemon user, Pokemon target, Battle battle) {
        if (getRange(user) == Range.SELF) {
            target = user;
        }

        this.decrementPP();
        if (target != user && target.getAbility().equals(PRESSURE) && this.getCurrentPP() > 0 && pressureApplies(user,
            target)) {
            this.decrementPP();
        }

        if (this.category == Category.STATUS && user != target && target.hasVolatileStatus(MAGIC_COAT)) {
            target = user;
        }

        executeMove(user, target, battle);

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

    protected void executeMove(Pokemon user, Pokemon target, Battle battle) {
        if (rollToHit(user, target, battle)) {
            if (this.category == Category.STATUS) {
                this.trySecondaryEffect(user, target, battle);
            } else {
                final int previousTargetHP = target.getCurrentHP();

                int hitNum = 0;
                final int maxHits = this.getNumHits(user.getAbility() == SKILL_LINK, user.getHeldItem() == LOADED_DICE);
                while (hitNum < maxHits && !user.hasFainted() && !target.hasFainted()) {
                    int damage = calculateDamage(user, target, battle);
                    target.takeDamage(damage);

                    if (!user.hasFainted()) {
                        this.inflictRecoil(user, damage);
                    }

                    target.afterHit(user, battle, previousTargetHP, this);
                    hitNum++;
                }
                if (hitNum > 1) {
                    System.out.println("Hit " + hitNum + " times!");
                }

                this.trySecondaryEffect(user, target, battle);

                if (!user.hasFainted()) {
                    user.afterAttack(target, battle, this);
                }
            }
        }
    }

    protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
        double movePower = calculateMovePower(user, target, battle);
        //TODO: Critical hits should ignore attack drops and defense buffs.
        double attack = this.category == Category.PHYSICAL ? user.calculateAttack() : user.calculateSpecialAttack();
        double defense =
            this.category == Category.PHYSICAL ? target.calculateDefense() : target.calculateSpecialDefense();
        //TODO: Deal with multi-hit moves and the weirdness that is Beat Up.

        double baseDamage = ((0.4 * user.getLevel() + 2) * movePower * (attack / defense) * 0.02) + 2;
        return applyMultipliers(user, target, battle, baseDamage);
    }

    protected int applyMultipliers(Pokemon user, Pokemon target, Battle battle, double baseDamage) {
        boolean isCritical = rollForCrit(user, target, battle);
        double typeMultiplier = this.getTypeMultiplier(user, target, battle);

        //TODO: Wait, what am I doing with this again? Is this for gems or what?
        double userAbilityItemMultipliers = user.beforeAttack(target, battle, this);

        typeMultiplier *= target.beforeHit(user, battle, this);

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
        } else if (battle.getWeather() == Weather.EXTREME_WIND && !battle.isWeatherSuppressed()) {
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
        double basePower = this.getPower() * this.getPowerMultipliers(user, target, battle);
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

    protected boolean rollForCrit(Pokemon user, Pokemon target, Battle battle) {
        //TODO: implement
        return false;
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

    protected int getAttackingStat(Pokemon attacker, Pokemon defender) {
        if (category == Category.PHYSICAL) {
            return attacker.calculateAttack();
        } else if (category == Category.SPECIAL) {
            return attacker.calculateSpecialAttack();
        } else {
            return 0;
        }
    }

    protected int getDefendingStat(Pokemon attacker, Pokemon defender) {
        if (category == Category.PHYSICAL) {
            return defender.calculateDefense();
        } else if (category == Category.SPECIAL) {
            return defender.calculateSpecialDefense();
        } else {
            return 0;
        }
    }

    protected void inflictRecoil(Pokemon user, int damageDealt) {
    }
}
