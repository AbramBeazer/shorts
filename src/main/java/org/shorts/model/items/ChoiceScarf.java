package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

public class ChoiceScarf extends HeldItem {

    private ChoiceScarf() {
        super("Choice Scarf", 10);
    }

    public static final ChoiceScarf CHOICE_SCARF = new ChoiceScarf();

    @Override
    public double onCalculateSpeed(Pokemon self) {
        return 1.5;
    }

    @Override
    public void afterAttack(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        if (!user.hasVolatileStatus(VolatileStatusType.CHOICE_LOCKED)) {
            user.addVolatileStatus(new VolatileStatus(VolatileStatusType.CHOICE_LOCKED, -1, move));
        }
    }
}
