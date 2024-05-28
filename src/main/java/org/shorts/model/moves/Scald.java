package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class Scald extends Move {

    public Scald() {
        super("Scald", 80, 100, Type.WATER, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 24, false, 30);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        //TODO: Does the thawing happen before the burn chance? Can they happen on the same turn?
        if (defender.getStatus().getType().equals(StatusType.FREEZE)) {
            System.out.println(defender.getNickname() + " was thawed out!");
            defender.setStatus(Status.NONE);
        }
        if (Status.BURN.isStatusPossible(defender, battle)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.setStatus(Status.BURN);
    }
}