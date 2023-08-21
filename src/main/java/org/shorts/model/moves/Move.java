package org.shorts.model.moves;

import java.util.Objects;
import java.util.Set;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public abstract class Move {

    public static enum MoveGroup {
        STATUS,
        PHYSICAL,
        SPECIAL
    }

    private String name;
    private double power;
    private double accuracy;
    private Type type;
    private MoveGroup moveGroup;

    private int currentPP;
    private int maxPP;

    private boolean contact;

    private int priority;

    private boolean disabled = false;

    protected Move(
        String name,
        double power,
        double accuracy,
        Type type,
        MoveGroup moveGroup,
        int maxPP,
        boolean contact) {
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.type = type;
        this.moveGroup = moveGroup;
        this.maxPP = maxPP;
        this.currentPP = maxPP;
        this.contact = contact;
        this.priority = 0;
    }

    protected Move(
        String name,
        double power,
        double accuracy,
        Type type,
        MoveGroup moveGroup,
        int maxPP,
        boolean contact, int priority) {
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.type = type;
        this.moveGroup = moveGroup;
        this.maxPP = maxPP;
        this.currentPP = maxPP;
        this.contact = contact;
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

    public MoveGroup getMoveGroup() {
        return this.moveGroup;
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

    public boolean isDisabled() {
        return disabled;
    }

    public double getMultiplier(Set<Type> attackerTypes, Set<Type> defenderTypes) throws Exception {
        return Type.getMultiplier(attackerTypes, this.type, defenderTypes);
    }

    public void secondaryEffect(Pokemon attacker, Pokemon defender) {
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
            && this.type.equals(other.type) && this.moveGroup.equals(other.moveGroup) && this.maxPP == other.maxPP
            && this.contact == other.contact && this.priority == other.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, power, accuracy, type, moveGroup, maxPP, contact, priority);
    }
}
