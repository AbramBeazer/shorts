package org.shorts.model.abilities.trapping;

import org.shorts.model.abilities.Ability;
import org.shorts.model.moves.Range;

public class ArenaTrap extends Ability {

    private ArenaTrap() {
        super("Arena Trap", Range.ALL_ADJACENT_OPPONENTS);
    }

    public static final ArenaTrap ARENA_TRAP = new ArenaTrap();
}
