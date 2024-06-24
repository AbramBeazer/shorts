package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

public class ChoiceBand extends HeldItem {

    private ChoiceBand() {
        super("Choice Band", 10);
    }

    public static final ChoiceBand CHOICE_BAND = new ChoiceBand();

    @Override
    public double getAttackMultipliers(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        return move.getCategory() == Move.Category.PHYSICAL ? 1.5 : 1;
    }

    @Override
    public void afterAttack(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        if (!user.hasVolatileStatus(VolatileStatusType.CHOICE_LOCKED)) {
            user.addVolatileStatus(new VolatileStatus(VolatileStatusType.CHOICE_LOCKED, -1, move));
        }
    }

}
