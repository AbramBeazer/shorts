package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

public class AttackDoublingAbility extends Ability {

    private AttackDoublingAbility(String name) {
        super(name);
    }

    public static final AttackDoublingAbility PURE_POWER = new AttackDoublingAbility("Pure Power");

    @Override
    public void onGainAbility(Pokemon self) {
        self.setAttack(self.getAttack() * 2);
    }

    @Override
    public void onLoseAbility(Pokemon self, Pokemon opponent, Battle battle) {
        self.setAttack(self.getAttack() / 2);
    }
}
