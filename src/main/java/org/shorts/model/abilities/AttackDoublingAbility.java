package org.shorts.model.abilities;

import org.shorts.model.pokemon.Pokemon;

public class AttackDoublingAbility extends Ability {

    private AttackDoublingAbility(String name) {
        super(name);
    }

    public static final AttackDoublingAbility PURE_POWER = new AttackDoublingAbility("Pure Power");
    public static final AttackDoublingAbility HUGE_POWER = new AttackDoublingAbility("Huge Power");

    @Override
    public double onCalculateAttack(Pokemon self) {
        return 2;
    }

}
