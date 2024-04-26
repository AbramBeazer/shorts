package org.shorts.model.moves;

import java.util.Objects;

import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.TooManyTypesException;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.SereneGrace.SERENE_GRACE;

public abstract class Move {

    private String name;
    private double power;
    private double accuracy;
    private Type type;

    private int currentPP;
    private int maxPP;

    private boolean contact;

    private int priority;
    private int secondaryEffectChance;

    private boolean disabled = false;

    protected Move(
        String name,
        double power,
        double accuracy,
        Type type,
        int maxPP,
        boolean contact, int secondaryEffectChance) {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority() {
        this.priority = priority;
    }

    public int getSecondaryEffectChance() {
        return secondaryEffectChance;
    }

    public void setSecondaryEffectChance(int secondaryEffectChance) {
        this.secondaryEffectChance = secondaryEffectChance;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public double getMultiplier(Pokemon attacker, Pokemon defender, Battle battle) throws TooManyTypesException {
        return Type.getMultiplier(attacker.getTypes(), this.type, defender.getTypes());
    }

    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        final int chance = getSecondaryEffectChance() * (attacker.getAbility().equals(SERENE_GRACE) ? 2 : 1);
        if (Main.RANDOM.nextInt(100) < chance) {
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

    public void doMove(Trainer user, Trainer target, Battle battle) {
        if (this instanceof StatusMove && target.getLead()
            .getAbility()
            .getName()
            .equals("Magic Bounce")) {
            target = user;
        }
        Pokemon userMon = user.getLead();
        Pokemon targetMon = target.getLead();

        if (this instanceof StatusMove) {
            this.trySecondaryEffect(userMon, targetMon, battle);
        } else {
            int previousTargetHP = targetMon.getCurrentHP();
            Integer damage = calculateDamage(userMon, targetMon);
            userMon.beforeAttack(userMon, targetMon, battle, damage, this.getType());
            targetMon.beforeHit(targetMon, userMon, battle, damage, this.getType());
            targetMon.takeDamage(damage);
            targetMon.afterHit(targetMon, userMon, battle, previousTargetHP);

            //TODO: Handle Endure, Destiny Bond, Perish Song, etc.

            if (targetMon.getCurrentHP() == 0) {
                targetMon.afterFaint(targetMon, userMon, battle);
                userMon.afterKO(userMon, targetMon, battle);
                //TODO: Handle fainting and subsequent switch-in.

            }

            //TODO: Handle recoil damage
            //if(userMon.getCurrentHP() == 0) {
            //  //TODO: Handle fainting and subsequent switch-in.
            //}
        }

    }

    private int calculateDamage(Pokemon userMon, Pokemon targetMon) {
        int damage = 0;
        double multiplier = Type.getMultiplier(
            userMon.getTypes(),
            this.getType(),
            targetMon.getTypes());

        if (multiplier == Type.IMMUNE) {
            //TODO: LOGGER.info("It didn't affect {}", target.getLead().getNickname());
            return 0;
        }
        //If the target isn't immune to the attack,
        return damage <= 0 ? 1 : (int) (damage * multiplier);
    }

}
