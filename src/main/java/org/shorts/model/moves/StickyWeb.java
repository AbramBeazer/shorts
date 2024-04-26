package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class StickyWeb extends StatusMove {

    private StickyWeb() {
        super("Sticky Web", 0, Type.BUG, 32);
    }

    public static final StickyWeb STICKY_WEB = new StickyWeb();
}
