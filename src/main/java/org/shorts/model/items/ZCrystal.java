package org.shorts.model.items;

import org.shorts.model.moves.Move;
import org.shorts.model.types.Type;

public class ZCrystal extends HeldItem {

    private Type type;
    private Move zMove;

    private ZCrystal(String name, Type type, Move zMove) {
        super(name);
        this.type = type;
        this.zMove = zMove;
    }

    public Type getType() {
        return type;
    }

    public Move getZMove() {
        return zMove;
    }
    //TODO: Implement
}
