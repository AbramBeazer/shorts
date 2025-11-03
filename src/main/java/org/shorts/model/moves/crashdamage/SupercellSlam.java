package org.shorts.model.moves.crashdamage;

import org.shorts.model.moves.HitsMinimize;
import org.shorts.model.types.Type;

public class SupercellSlam extends CrashDamageMove implements HitsMinimize {

    public SupercellSlam() {
        super("Supercell Slam", 100, 95, Type.ELECTRIC, 24, 0);
    }
}
