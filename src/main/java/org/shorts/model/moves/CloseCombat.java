package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class CloseCombat extends PhysicalMove {

    private CloseCombat() {
        super("Close Combat", 120, 100, Type.FIGHTING, 8, true, 100);
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        attacker.changeDefense(-1);
        attacker.changeSpecialDefense(-1);
        attacker.afterDrop(attacker, defender, battle);
    }

    public static final CloseCombat CLOSE_COMBAT = new CloseCombat();
}
