package org.shorts.model.items;

public final class NoItem extends HeldItem {

    private NoItem() {
        super("No Item", 0);
    }

    public static final NoItem NO_ITEM = new NoItem();
}
