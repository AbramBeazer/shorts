package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

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

    public double onMovePowerCalc(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return 1;
    }

    public void beforeAttack(Pokemon self, Pokemon opponent, Battle battle, Move move) {
    }

    public void afterAttack(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public void afterDrop(Pokemon self, Pokemon opponent, Battle battle) {
    }

    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return 1;
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

    public void beforeSwitchOut(Pokemon self, Pokemon opponent, Battle battle) {

    }

    public void onGainAbility(Pokemon self) {

    }

    public void onLoseAbility(Pokemon self, Pokemon opponent, Battle battle) {

    }

}
