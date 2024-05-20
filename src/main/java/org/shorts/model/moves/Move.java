package org.shorts.model.moves;

import java.util.Objects;
import java.util.Set;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.battle.Weather;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.TooManyTypesException;
import org.shorts.model.types.Type;

import static org.shorts.Main.RANDOM;
import static org.shorts.model.abilities.Pressure.PRESSURE;
import static org.shorts.model.abilities.SereneGrace.SERENE_GRACE;
import static org.shorts.model.status.VolatileStatusType.MAGIC_COAT;
import static org.shorts.model.types.Type.FIRE;
import static org.shorts.model.types.Type.IMMUNE;
import static org.shorts.model.types.Type.WATER;

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
        boolean isCritical = rollForCrit(user, target, battle);
        double movePower = calculateMovePower(user, target, battle);
        //TODO: Critical hits should ignore attack drops and defense buffs.
        double attack = this instanceof PhysicalMove ? user.calculateAttack() : user.calculateSpecialAttack();
        double defense = this instanceof PhysicalMove ? target.calculateDefense() : target.calculateSpecialDefense();
        //TODO: Deal with weird edge cases like Foul Play, Psyshock, and Beat Up.
        //  Maybe I'll have a "getAttackingStat" or "getDefendingStat" method in Phys/Spec Move that I override in edge case classes.

        int baseDamage = (int) ((0.4 * user.getLevel() + 2) * movePower * (attack / defense) * 0.02) + 2;
        return applyMultipliers(user, target, battle, baseDamage);
    }

    protected int applyMultipliers(Pokemon user, Pokemon target, Battle battle, int baseDamage) {
        double typeMultiplier = this.getTypeMultiplier(target.getTypes());

        double userAbilityItemMultipliers = user.beforeAttack(target, battle, this);

        typeMultiplier *= target.beforeHit(user, battle, this);

        if (typeMultiplier == IMMUNE) {
            //TODO: LOGGER.info("It didn't affect {}", target.getLead().getNickname());
            return 0;
        }

        double stabMultiplier = getSTABMultiplier(user.getTypes());

        //If the target isn't immune to the attack,
        return baseDamage <= 0 ? 1 : (int) (baseDamage * typeMultiplier * stabMultiplier);
    }

    protected double getTypeMultiplier(Set<Type> defenderTypes) throws TooManyTypesException {
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

    private double getOtherMultiplier(Pokemon user, Pokemon target) {
        return 1;
        //TODO: Implement
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
