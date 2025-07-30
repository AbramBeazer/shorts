package org.shorts.model.items;

import java.util.Objects;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public abstract class HeldItem {

    private String name;
    private int flingPower;

    protected HeldItem(String name, int flingPower) {
        this.name = name;
        this.flingPower = flingPower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlingPower() {
        return flingPower;
    }

    public void afterEntry(Pokemon self, Battle battle) {
    }

    public double getMovePowerMultipliers(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        return 1;
    }

    public double getAttackMultipliers(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        return 1;
    }

    public double getDefenseMultipliers(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        return 1;
    }

    public void beforeAttack(Pokemon self, Pokemon opponent) {
    }

    public void afterAttack(Pokemon user, Pokemon opponent, Battle battle, Move move) {
    }

    public boolean isDropPossible(StatEnum stat) {
        return true;
    }

    public void afterDrop(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return 1;
    }

    public void afterHit(Pokemon user, Pokemon opponent, Battle battle, int previousHP, Move move) {
    }

    public void afterStatus(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public void beforeTurn(Pokemon self, Pokemon opponent, Battle battle) {

    }

    public void afterTurn(Pokemon user, Battle battle) {
    }

    public void afterFaint(Pokemon self, Battle battle) {
    }

    public void afterKO(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public void beforeSwitchOut(Pokemon self, Pokemon opponent, Battle battle) {

    }

    public void onWeatherChange(Pokemon self, Battle battle) {

    }

    public void onTerrainChange(Pokemon self, Battle battle) {

    }

    public void onGainItem(Pokemon self, Battle battle) {

    }

    public void onLoseItem(Pokemon self, Pokemon opponent, Battle battle) {

    }

    public double onCalculateAttack(Pokemon self) {
        return 1;
    }

    public double onCalculateDefense(Pokemon self) {
        return 1;
    }

    public double onCalculateSpecialAttack(Pokemon self) {
        return 1;
    }

    public double onCalculateSpecialDefense(Pokemon self) {
        return 1;
    }

    public double onCalculateSpeed(Pokemon self) {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof HeldItem) {
            HeldItem item = (HeldItem) obj;
            return name.equals(item.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
