package org.shorts.model.items;

import org.shorts.model.types.Type;

public class Mask extends HeldItem {

    private Type type;

    private Mask(String name, Type type) {
        super(name, 0);
        this.type = type;
    }

    public static final Mask WELLSPRING_MASK = new Mask("Wellspring Mask", Type.WATER);
    public static final Mask HEARTHFLAME_MASK = new Mask("Hearthflame Mask", Type.FIRE);
    public static final Mask CORNERSTONE_MASK = new Mask("Cornerstone Mask", Type.ROCK);

    public Type getType() {
        return type;
    }

    //TODO: Implement
}
