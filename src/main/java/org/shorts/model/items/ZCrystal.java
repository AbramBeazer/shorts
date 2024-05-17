package org.shorts.model.items;

import org.shorts.model.types.Type;

public class ZCrystal extends HeldItem {

    private Type type;

    private ZCrystal(String name, Type type) {
        super(name);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public static final ZCrystal BUGINIUM_Z = new ZCrystal("Buginium Z", Type.BUG);
    //TODO: Implement
    // No Z-Moves in Gen 9

}
