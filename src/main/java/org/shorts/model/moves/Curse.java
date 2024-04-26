package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.types.Type.GHOST;

public class Curse extends StatusMove {

    private Curse() {
        super("Curse", 0, GHOST, 16);
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (attacker.getTypes().contains(GHOST)) {
            final int previousHP = attacker.getCurrentHP();
            attacker.takeDamage(attacker.getMaxHP() / 2);
            attacker.afterHit(attacker, defender, battle, previousHP);
            //TODO: Add "cursed" status to defender
        } else {
            attacker.changeSpeed(-1);
            attacker.changeAttack(1);
            attacker.changeDefense(1);
        }
    }

    public static final Curse CURSE = new Curse();
}
