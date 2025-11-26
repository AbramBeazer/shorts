package org.shorts.model.moves.switchself;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.items.EjectButton.*;
import static org.shorts.model.items.RedCard.*;

public class VoltSwitch extends Move implements SwitchSelfMove {

    public VoltSwitch() {
        super("Volt Switch", 70, 100, Type.ELECTRIC, Category.SPECIAL, Range.NORMAL, 32, false, 100);
    }

    @Override
    public boolean canSwitchSelf(Pokemon user, Pokemon target, Battle battle) {
        //&& !triggers target's Wimp Out
        //&& !triggers target's Emergency Exit
        return user.getHeldItem() != RED_CARD && target.getHeldItem() != EJECT_BUTTON;
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        final Trainer attackingTrainer = battle.getCorrespondingTrainer(user);
        final Trainer defendingTrainer = battle.getCorrespondingTrainer(target);

        if (attackingTrainer.hasAvailableSwitch() && !defendingTrainer.hasLost()
            && canSwitchSelf(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        //TODO: Figure out how to make the switch happen here.
        //TODO: Make sure this works with Pursuit.
    }
}
