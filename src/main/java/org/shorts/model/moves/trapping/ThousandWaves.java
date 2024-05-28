package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class ThousandWaves extends Move implements TrappingMove {

    //Not in Gen 9
    public ThousandWaves() {
        super("Thousand Waves", 90, 100, Type.GROUND, 16, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        applySecondaryEffect(attacker, defender, battle);
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        TrappingMove.super.applyCantEscapeStatus(defender);
    }

}
