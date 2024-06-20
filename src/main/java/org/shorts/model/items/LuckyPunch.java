package org.shorts.model.items;

public class LuckyPunch extends HeldItem {

    private LuckyPunch() {
        super("Lucky Punch", 30);
    }

    public static final LuckyPunch LUCKY_PUNCH = new LuckyPunch();
}
