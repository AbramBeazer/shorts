package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;

import static org.shorts.model.types.Type.GHOST;

public class Curse extends Move {

    public Curse() {
        super("Curse", 0, -1, GHOST, Category.STATUS, Range.VARIES, 16, true, 100);
    }

    @Override
    public Range getRange(Pokemon user) {
        if (user.getTypes().contains(GHOST)) {
            return Range.RANDOM_OPPONENT;
        } else {
            return Range.SELF;
        }
    }

    @Override
    public void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (user.getTypes().contains(GHOST)) {
            final int previousHP = user.getCurrentHP();
            user.takeDamage(user.getMaxHP() / 2);

            //TODO: Should this be afterHit, or should I have another listener for self-inflicted damage?
            //TODO: Should this only activate if the user hasn't fainted?
            user.afterHit(target, battle, previousHP, this);
            target.addVolatileStatus(VolatileStatus.CURSED);
            //TODO: LOGGER.info("{} put a curse on {}!", attacker.getNickname(), defender.getNickname());
        } else {
            user.changeStat(1, StatEnum.ATK);
            user.changeStat(1, StatEnum.DEF);
            user.changeStat(-1, StatEnum.SPEED);
        }
    }

}
