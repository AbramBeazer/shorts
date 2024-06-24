package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class AttackDoublingAbility extends Ability {

    private AttackDoublingAbility(String name) {
        super(name);
    }

    public static final AttackDoublingAbility PURE_POWER = new AttackDoublingAbility("Pure Power");
    public static final AttackDoublingAbility HUGE_POWER = new AttackDoublingAbility("Huge Power");

    @Override
    public double getAttackMultipliers(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return 2;
    }

}
