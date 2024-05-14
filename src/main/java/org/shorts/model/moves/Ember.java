package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class Ember extends SpecialMove {

    public Ember() {
        super("Ember", 40, 100, Type.FIRE, 40, false, 10);
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
