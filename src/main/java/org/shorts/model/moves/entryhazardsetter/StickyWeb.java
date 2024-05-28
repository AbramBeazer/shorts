package org.shorts.model.moves.entryhazardsetter;

import org.shorts.battle.Battle;
import org.shorts.model.moves.StatusMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class StickyWeb extends StatusMove implements EntryHazardSetter {

    public StickyWeb() {
        super("Sticky Web", 0, Type.BUG, 32, false);
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        //TODO:        Trainer opposingTrainer =
    }

    @Override
    protected boolean pressureApplies(Pokemon userMon, Pokemon targetMon) {
        return false;
    }
}
