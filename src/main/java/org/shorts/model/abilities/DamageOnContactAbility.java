package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class DamageOnContactAbility extends Ability {

    private DamageOnContactAbility() {
        super("Rough Skin");
    }

    public static final DamageOnContactAbility ROUGH_SKIN = new DamageOnContactAbility();
    public static final DamageOnContactAbility IRON_BARBS = new DamageOnContactAbility();

    @Override
    public void afterHit(Pokemon self, Pokemon opponent, Battle battle, int previousHP, Move move) {
        if (move.isContact(opponent)) {
            opponent.takeDamage(opponent.getMaxHP() / 8);
        }
    }
}
