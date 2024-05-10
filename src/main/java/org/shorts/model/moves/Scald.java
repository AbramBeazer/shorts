package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

public class Scald extends SpecialMove {

    private Scald() {
        super("Scald", 80, 100, Type.WATER, 24, false, 30);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        //TODO: Does the thawing happen before the burn chance? Can they happen on the same turn?
        if (defender.getStatus().getType().equals(Status.StatusType.FREEZE)) {
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

    public static final Scald SCALD = new Scald();
}
