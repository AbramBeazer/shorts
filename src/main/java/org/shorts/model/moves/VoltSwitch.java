package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class VoltSwitch extends SpecialMove {

    public VoltSwitch() {
        super("Volt Switch", 70, 100, Type.ELECTRIC, 32, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        final Trainer attackingTrainer =
            battle.getPlayerOne().getLead() == attacker ? battle.getPlayerOne() : battle.getPlayerTwo();
        final Trainer defendingTrainer =
            attackingTrainer == battle.getPlayerOne() ? battle.getPlayerTwo() : battle.getPlayerOne();

        if (attackingTrainer.hasAvailableSwitch() && !defendingTrainer.hasLost()
            //TODO:
            //&& !triggers defender's Wimp Out
            //&& !triggers defender's Emergency Exit
            //&& Volt Switch will not force a switch if the user is holding a Red Card or the target is holding an Eject Button.
        ) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        //TODO: Figure out how to make the switch happen here.
        //TODO: Make sure this works with Pursuit.
    }
}
