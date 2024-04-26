package org.shorts.model.items;

public class NoItem extends HeldItem {

    private NoItem() {
        super("No Item");
    }

    public static final NoItem NO_ITEM = new NoItem();
}
