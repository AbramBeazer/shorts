package org.shorts.model.moves.switchself;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.moves.Move.Category.STATUS;

public class BatonPass extends Move implements SwitchSelfMove {

    public BatonPass() {
        super("Baton Pass", 0, -1, Type.NORMAL, STATUS, Range.SELF, 64, true, 100);
    }

    //TODO: Implement these
    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        super.trySecondaryEffect(user, target, battle);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {

    }
}
