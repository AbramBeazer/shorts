package org.shorts.model.moves.entryhazardsetter;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class StickyWeb extends Move implements EntryHazardSetter {

    public StickyWeb() {
        super("Sticky Web", 0, -1, Type.BUG, Category.STATUS, Range.OTHER_SIDE, 32, false, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        //TODO:        Trainer opposingTrainer =
    }

    @Override
    protected boolean pressureApplies(Pokemon userMon, Pokemon targetMon) {
        return false;
    }
}
