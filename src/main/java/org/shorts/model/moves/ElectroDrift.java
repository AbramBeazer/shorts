package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class ElectroDrift extends SpecialMove implements ExtraSuperEffectiveDamageAttack {
    public ElectroDrift() {
        super("Electro Drift", 100, 100, Type.ELECTRIC, 8, true, 100);
    }
}
