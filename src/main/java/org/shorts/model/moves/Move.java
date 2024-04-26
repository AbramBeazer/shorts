package org.shorts.model.moves;

import java.util.Objects;

import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
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

    protected Move(
        String name,
        double power,
        double accuracy,
        Type type,
        int maxPP,
        boolean contact, int secondaryEffectChance, int priority) {
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.type = type;
        this.maxPP = maxPP;
        this.currentPP = maxPP;
        this.contact = contact;
        this.secondaryEffectChance = secondaryEffectChance;
        this.priority = priority;
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

    public void setSecondaryEffectChance(int secondaryEffectChance) {
        this.secondaryEffectChance = secondaryEffectChance;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public double getMultiplier(Pokemon attacker, Pokemon defender, Battle battle) throws Exception {
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
}
