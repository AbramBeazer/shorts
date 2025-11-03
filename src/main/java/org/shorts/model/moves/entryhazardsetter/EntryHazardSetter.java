package org.shorts.model.moves.entryhazardsetter;

import org.shorts.model.moves.AffectedByMagicBounce;

public interface EntryHazardSetter extends AffectedByMagicBounce {

    @Override
    default boolean setsEntryHazards() {
        return true;
    }
}
