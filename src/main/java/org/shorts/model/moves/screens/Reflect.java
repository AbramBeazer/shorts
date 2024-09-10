package org.shorts.model.moves.screens;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.items.LightClay.LIGHT_CLAY;

public class Reflect extends Move {

    public Reflect() {
        super("Reflect", 0, -1, Type.PSYCHIC, Category.STATUS, Range.OWN_SIDE, 32, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (battle.getCorrespondingTrainer(user).getReflectTurns() <= 0) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        final int turns = user.getHeldItem() == LIGHT_CLAY ? 8 : 5;
        battle.getCorrespondingTrainer(user).setReflectTurns(turns);
    }
}
