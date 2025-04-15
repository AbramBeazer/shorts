package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class ElectroDrift extends Move implements ExtraSuperEffectiveDamageAttack {

    public ElectroDrift() {
        super("Electro Drift", 100, 100, Type.ELECTRIC, Category.SPECIAL, Range.NORMAL, 8, true, 100);
    }
}
