package org.shorts.model.moves.crashdamage;

import java.io.Serializable;

import org.shorts.model.moves.KickingMove;
import org.shorts.model.types.Type;

public class HighJumpKick extends CrashDamageMove implements KickingMove {

    public HighJumpKick() {
        super("High Jump Kick", 130, 90, Type.FIGHTING, 16, 0);
    }
}
