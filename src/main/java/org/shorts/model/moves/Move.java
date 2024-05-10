package org.shorts.model.moves;

import java.util.Objects;
import java.util.Set;

import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.TooManyTypesException;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Pressure.PRESSURE;
import static org.shorts.model.abilities.SereneGrace.SERENE_GRACE;
import static org.shorts.model.moves.Curse.CURSE;
import static org.shorts.model.types.Type.GHOST;
import static org.shorts.model.types.Type.IMMUNE;

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
    private final boolean targetSelf;
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
        this.targetSelf = false;
    }

    protected Move(
        String name,
        double power,
        double accuracy,
        Type type,
        int maxPP,
        boolean contact,
        int secondaryEffectChance,
        boolean targetSelf) {
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.type = type;
        this.maxPP = maxPP;
        this.currentPP = maxPP;
        this.contact = contact;
        this.secondaryEffectChance = secondaryEffectChance;
        this.priority = 0;
        this.targetSelf = targetSelf;
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

    public int getSecondaryEffectChance() {
        return secondaryEffectChance;
    }

    public boolean isTargetSelf() {
        return targetSelf;
    }

    public boolean isDisabled() {
        return disabled;
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

    public void doMove(Trainer user, Trainer opponent, Battle battle) {
        if (this instanceof StatusMove && opponent.getLead().getAbility().getName().equals("Magic Bounce")) {
            opponent = user;
        }
        Pokemon userMon = user.getLead();
        Pokemon opponentMon = opponent.getLead();

        this.decrementPP();
        if (userMon != opponentMon && opponentMon.getAbility().equals(PRESSURE) && this.getCurrentPP() > 0
            && (!this.isTargetSelf() || (this == CURSE && userMon.getTypes().contains(GHOST)))) {
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

    private int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
        double movePower = calculateMovePower(user, target, battle);
        double attack = this instanceof PhysicalMove ? user.getAttack() : user.getSpecialAttack();
        double defense = this instanceof PhysicalMove ? user.getDefense() : user.getSpecialDefense();
        //TODO: Deal with weird edge cases like Foul Play, Psyshock, and Beat Up.

        int baseDamage = (int) ((0.4 * user.getLevel() + 2) * movePower * (attack / defense) * 0.02) + 2;
        return applyMultipliers(user, target, battle, baseDamage);
    }

    protected int applyMultipliers(Pokemon user, Pokemon target, Battle battle, int baseDamage) {
        double typeMultiplier = this.getTypeMultiplier(target.getTypes());

        user.beforeAttack(target, battle, this);

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

    private double calculateMovePower(Pokemon user, Pokemon target, Battle battle) {
        double basePower = this.getPower();
        basePower *= user.onMovePowerCalc(target, battle, this);
        //TODO: Handle weather multipliers, terrain multipliers, mud sport, etc.
        //TODO: Investigate what, if anything, I need to do on the target's side of things.
        return basePower;
    }

    protected void decrementPP() {
        if (this.currentPP > 0) {
            this.currentPP--;
        }
    }
}
