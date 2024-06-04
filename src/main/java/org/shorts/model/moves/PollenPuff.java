package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Bulletproof;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Telepathy.TELEPATHY;

public class PollenPuff extends Move implements BallBombMove {

    public PollenPuff() {
        super("Pollen Puff", 90, 100, Type.BUG, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 24, false, 0);
    }

    @Override
    protected void executeMove(Pokemon user, Pokemon target, Battle battle) {
        if (battle.getCorrespondingTrainer(user) != battle.getCorrespondingTrainer(target)) {
            super.executeMove(user, target, battle);
        } else {
            if (rollToHit(user, target, battle) && target.getAbility() != Bulletproof.BULLETPROOF
                && target.getAbility() != TELEPATHY && !target.hasVolatileStatus(VolatileStatusType.HEAL_BLOCKED)) {
                target.heal(target.getMaxHP() / 2);
            }
        }
    }
}
