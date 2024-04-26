package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public abstract class Ability {

    private String name;

    protected Ability(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void afterEntry(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public void beforeAttack(Pokemon self, Pokemon opponent, Battle battle, Integer damage, Type moveType) {
    }

    public void afterAttack(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public void afterDrop(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public void beforeHit(Pokemon self, Pokemon opponent, Battle battle, Integer damage, Type moveType) {
    }

    public void afterHit(Pokemon self, Pokemon opponent, Battle battle, int previousHP) {
    }

    public void afterStatus(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public void beforeTurn(Pokemon self, Pokemon opponent, Battle battle) {

    }

    public void afterTurn(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public void afterFaint(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public void afterKO(Pokemon self, Pokemon opponent, Battle battle) {
    }

}
