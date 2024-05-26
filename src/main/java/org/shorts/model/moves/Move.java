package org.shorts.model.moves;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.battle.Weather;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.TooManyTypesException;
import org.shorts.model.types.Type;

import static org.shorts.Main.RANDOM;
import static org.shorts.MathUtils.roundHalfDown;
import static org.shorts.MathUtils.roundHalfUp;
import static org.shorts.model.abilities.Pressure.PRESSURE;
import static org.shorts.model.abilities.Scrappy.SCRAPPY;
import static org.shorts.model.abilities.SereneGrace.SERENE_GRACE;
import static org.shorts.model.items.IronBall.IRON_BALL;
import static org.shorts.model.items.RingTarget.RING_TARGET;
import static org.shorts.model.status.VolatileStatusType.*;
import static org.shorts.model.types.Type.*;

public abstract class Move {

    private final String name;
    private final double power;
    private final double accuracy;
    private final Type type;

    private int currentPP;
    private final int maxPP;

    private final boolean contact;

    private final int priority;
    private final int secondaryEffectChance;
    private boolean disabled = false;

    protected Move(
            String name, double power, double accuracy, Type type, int maxPP, boolean contact, int secondaryEffectChance) {
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.type = type;
        this.maxPP = maxPP;
        this.currentPP = maxPP;
        this.contact = contact;
        this.secondaryEffectChance = secondaryEffectChance;
        this.priority = 0;
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
        return priority;
    }

    public int getSecondaryEffectChance() {
        return secondaryEffectChance;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        final int chance = getSecondaryEffectChance() * (attacker.getAbility().equals(SERENE_GRACE) ? 2 : 1);
        if (RANDOM.nextInt(100) < chance) {
            applySecondaryEffect(attacker, defender, battle);
        }
    }

    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
    }

    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        if (accuracy <= 0) return true;
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
                && this.priority == other.priority && this.secondaryEffectChance == other.secondaryEffectChance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, power, accuracy, type, maxPP, contact, priority, secondaryEffectChance);
    }

    public void doMove(Trainer user, Trainer opponent, Battle battle) {
        if (this instanceof StatusMove && opponent.getLead().hasVolatileStatus(MAGIC_COAT)) {
            opponent = user;
        }
        Pokemon userMon = user.getLead();
        Pokemon opponentMon = opponent.getLead();

        this.decrementPP();
        if (userMon != opponentMon && opponentMon.getAbility().equals(PRESSURE) && this.getCurrentPP() > 0
                && pressureApplies(userMon)) {
            this.decrementPP();
        }

        if (this instanceof StatusMove) {
            this.trySecondaryEffect(userMon, opponentMon, battle);
        } else {
            int previousTargetHP = opponentMon.getCurrentHP();
            int damage = calculateDamage(userMon, opponentMon, battle);
            opponentMon.takeDamage(damage);
            opponentMon.afterHit(userMon, battle, previousTargetHP, this);

            //TODO: Handle Endure, Destiny Bond, Perish Song, etc.

            if (opponentMon.getCurrentHP() == 0) {
                opponentMon.afterFaint(userMon, battle);
                userMon.afterKO(opponentMon, battle);
                //TODO: Handle fainting and subsequent switch-in.

            }

            //TODO: Handle recoil damage
            //if(userMon.getCurrentHP() == 0) {
            //  //TODO: Handle fainting and subsequent switch-in.
            //}
        }
    }

    protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
        double movePower = calculateMovePower(user, target, battle);
        //TODO: Critical hits should ignore attack drops and defense buffs.
        double attack = this instanceof PhysicalMove ? user.calculateAttack() : user.calculateSpecialAttack();
        double defense = this instanceof PhysicalMove ? target.calculateDefense() : target.calculateSpecialDefense();
        //TODO: Deal with multi-hit moves and the weirdness that is Beat Up.

        double baseDamage = ((0.4 * user.getLevel() + 2) * movePower * (attack / defense) * 0.02) + 2;
        return applyMultipliers(user, target, battle, baseDamage);
    }

    private int applyMultipliers(Pokemon user, Pokemon target, Battle battle, double baseDamage) {
        boolean isCritical = rollForCrit(user, target, battle);
        double typeMultiplier = this.getTypeMultiplier(user, target, battle);

        double userAbilityItemMultipliers = user.beforeAttack(target, battle, this);

        typeMultiplier *= target.beforeHit(user, battle, this);

        if (typeMultiplier == IMMUNE) {
            //TODO: LOGGER.info("It didn't affect {}", target.getLead().getNickname());
            return 0;
        }

        double stabMultiplier = getSTABMultiplier(user.getTypes());

        baseDamage = roundHalfDown(baseDamage * getNumTargetsMultiplier());
        baseDamage = roundHalfDown(baseDamage * getWeatherMultiplier(battle));
        baseDamage = roundHalfDown(baseDamage * getGlaiveRushMultiplier(target));
        baseDamage = roundHalfDown(baseDamage * getCriticalMultiplier(isCritical));
        baseDamage = roundHalfDown(baseDamage * getRandomMultiplier());
        baseDamage = roundHalfDown(baseDamage * stabMultiplier);
        baseDamage = roundHalfDown(baseDamage * typeMultiplier);
        baseDamage = roundHalfDown(baseDamage * getOtherMultiplier(user, target, battle));
        return (int) baseDamage;
    }

    protected double getTypeMultiplier(Pokemon user, Pokemon target, Battle battle) {
        double multiplier = getBaseTypeMultiplier(target.getTypes());
        if (!target.isGrounded() && target.getTypes().contains(FLYING) && (target.getHeldItem() == IRON_BALL)) {
            multiplier = 1;
        } else if (target.isGrounded() && target.getTypes().contains(FLYING) && this.type == GROUND && (target.getHeldItem() != IRON_BALL)) {
            multiplier = getBaseTypeMultiplier(target.getTypes().stream().filter(t -> t != FLYING).collect(Collectors.toSet()));
        } else if (multiplier == IMMUNE && (target.getHeldItem() == RING_TARGET || target.hasVolatileStatus(VolatileStatusType.IDENTIFIED))) {
            multiplier = getBaseTypeMultiplier(target.getTypes().stream().filter(t -> !t.getImmunities().contains(this.type.getId())).collect(Collectors.toSet()));
        } else if (user.getAbility() == SCRAPPY && (this.type == NORMAL || this.type == FIGHTING) && target.getTypes().contains(GHOST)) {
            multiplier = getBaseTypeMultiplier(target.getTypes().stream().filter(t -> t != GHOST).collect(Collectors.toSet()));
        } else if (battle.getWeather() == Weather.EXTREME_WIND && !battle.isWeatherSuppressed()) {
            multiplier = getBaseTypeMultiplier(target.getTypes().stream().filter(t -> t != FLYING).collect(Collectors.toSet()));
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

    private double calculateMovePower(Pokemon user, Pokemon target, Battle battle) {
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
        if (!battle.isWeatherSuppressed()) {
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
        return 1;
    }

    protected double getOtherMultiplier(Pokemon user, Pokemon target, Battle battle) {
        double base = 4096;
        if (this instanceof HitsMinimize && target.hasVolatileStatus(MINIMIZED)) {
            base = roundHalfUp(base * 2);
        }
        if ((this instanceof Earthquake || this instanceof Magnitude) && (target.hasVolatileStatus(SEMI_INVULNERABLE) && target.getVolatileStatus(SEMI_INVULNERABLE).getMove() instanceof Dig)) {
            base = roundHalfUp(base * 2);
        }
        if ((this instanceof Surf || this instanceof Whirlpool) && (target.hasVolatileStatus(SEMI_INVULNERABLE) && target.getVolatileStatus(SEMI_INVULNERABLE).getMove() instanceof Dive)) {
            base = roundHalfUp(base * 2);
        }
        Trainer opposingTrainer =
        if(
        )
    }

    private void decrementPP() {
        if (this.currentPP > 0) {
            this.currentPP--;
        }
    }

    protected boolean pressureApplies(Pokemon userMon) {
        return true;
    }

    protected abstract int getAttackingStat(Pokemon attacker, Pokemon defender);

    protected abstract int getDefendingStat(Pokemon attacker, Pokemon defender);
}
