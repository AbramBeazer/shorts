package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.shorts.battle.Terrain.MISTY;

public class Thunder extends SpecialMove {

    public Thunder() {
        super("Thunder", 120, 70, Type.ELECTRIC, 16, false, 30);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        Trainer trainer = battle.getPlayerOne().getLead() == defender ? battle.getPlayerOne() : battle.getPlayerTwo();
        if (defender.getStatus() != Status.NONE && !defender.getTypes().contains(Type.ELECTRIC)
            && !(defender.isGrounded() && battle.getTerrain() == MISTY)
            && trainer.getSafeguardTurns() > 0) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.setStatus(Status.PARALYZE);
    }

}
