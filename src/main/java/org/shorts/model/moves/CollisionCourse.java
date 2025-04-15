package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class CollisionCourse extends Move implements ExtraSuperEffectiveDamageAttack {

    public CollisionCourse() {
        super("Collision Course", 100, 100, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL, 8, true, 100);
    }
}
