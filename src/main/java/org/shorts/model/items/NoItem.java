package org.shorts.model.items;

public class NoItem extends HeldItem {

    public NoItem() {
        super("No Item");
    }

    public static NoItem NO_ITEM = new NoItem();
}
