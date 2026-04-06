package org.shorts.model.abilities;

import java.util.Objects;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;

/*TODO: Is it better to not use static singletons of abilities unless necessary?
     I thought it'd be better to use singletons so we wouldn't have, say,
     two instances of Intimidate taking up memory, but it seems like statically
     declaring an instance of each ability would take up a lot of memory.
     Some would still need to be singletons, like Pickup, maybe.*/
public abstract class Ability {

    private String name;
    private Range range;

    protected Ability(String name) {
        this(name, Range.SELF);
    }

    protected Ability(String name, Range range) {
        this.name = name;
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Range getRange() {
        return range;
    }

    public void afterEntry(Pokemon self, Battle battle) {
    }

    public double getMovePowerMultipliers(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return 1;
    }

    public double getAttackMultipliers(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return 1;
    }

    public double getDefenseMultipliers(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return 1;
    }

    public void beforeAttack(Pokemon self, Pokemon opponent) {
    }

    public void afterAttack(Pokemon self, Pokemon opponent, Battle battle, Move move) {
    }

    public boolean isDropPossible(StatEnum stat) {
        return true;
    }

    public void afterDrop(Pokemon self, Pokemon cause, Battle battle) {
    }

    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return 1;
    }

    public void afterHit(Pokemon self, Pokemon opponent, Battle battle, int previousHP, Move move) {
    }

    public void afterStatus(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public void beforeTurn(Pokemon self, Pokemon opponent, Battle battle) {

    }

    public void afterTurn(Pokemon self, Battle battle) {
    }

    public void afterFaint(Pokemon self, Battle battle) {
    }

    public void afterKO(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public void beforeSwitchOut(Pokemon self, Pokemon opponent, Battle battle) {

    }

    public void onInitiate(Pokemon self) {

    }

    public void onGainAbility(Pokemon self, Battle battle) {

    }

    public void onLoseAbility(Pokemon self, Pokemon opponent, Battle battle) {

    }

    public void onWeatherChange(Pokemon self, Battle battle) {

    }

    public void onTerrainChange(Pokemon self, Battle battle) {

    }

    public double getWeightMultiplier() {
        return 1;
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

        if (obj instanceof Ability) {

            if (obj.getClass() == this.getClass()) {
                return true;
            }
            Ability ability = (Ability) obj;
            return name.equals(ability.name);
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
