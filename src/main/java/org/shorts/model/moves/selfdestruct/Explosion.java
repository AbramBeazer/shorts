package org.shorts.model.moves.selfdestruct;

import org.shorts.model.types.Type;

//TODO: Make sure that Explosion behaves exactly like Self-Destruct.
// Bulbapedia says SD fails if it misses or the user is immune, but doesn't say that about Explosion.
public class Explosion extends SelfDestructingMove {

    public Explosion(){
        super("Explosion", 250, Type.NORMAL, Category.PHYSICAL);
    }
}
