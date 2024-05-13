package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class BatonPass extends StatusMove {

    public BatonPass() {
        super("Baton Pass", -1, Type.NORMAL, 64, true);
    }

    //TODO: Implement these
    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        super.trySecondaryEffect(attacker, defender, battle);
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {

    }
}
