package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;

import static org.shorts.model.types.Type.GHOST;
import static org.shorts.model.types.Type.NORMAL;

public class Curse extends StatusMove {

    private Curse() {
        super("Curse", 0, NORMAL, 16, true);
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (attacker.getTypes().contains(GHOST)) {
            final int previousHP = attacker.getCurrentHP();
            attacker.takeDamage(attacker.getMaxHP() / 2);

            //TODO: Should this be afterHit, or should I have another listener for self-inflicted damage?
            //TODO: Should this only activate if the user hasn't fainted?
            attacker.afterHit(defender, battle, previousHP, this);
            defender.addVolatileStatus(VolatileStatus.CURSED);
            //TODO: LOGGER.info("{} put a curse on {}!", attacker.getNickname(), defender.getNickname());
        } else {
            attacker.changeSpeed(-1);
            attacker.changeAttack(1);
            attacker.changeDefense(1);
        }
    }

    public static final Curse CURSE = new Curse();
}
