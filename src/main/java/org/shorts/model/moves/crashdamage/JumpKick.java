package org.shorts.model.moves.crashdamage;

import org.shorts.model.moves.KickingMove;
import org.shorts.model.types.Type;

public class JumpKick extends CrashDamageMove implements KickingMove {

    public JumpKick() {
        super("Jump Kick", 100, 95, Type.FIGHTING, 24, 0);
    }
}
