package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class XScissor extends Move implements SlicingMove {

    public XScissor() {
        super("X-Scissor", 80, 100, Type.BUG, Category.PHYSICAL, Range.NORMAL, 24, true, 0);
    }
}
