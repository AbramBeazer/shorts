package org.shorts.model.moves.crashdamage;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.moves.KickingMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class JumpKick extends CrashDamageMove implements KickingMove {

    public JumpKick() {
        super("Jump Kick", 100, 95, Type.FIGHTING, 24, 0);
    }

    @Override
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        if (battle.getGravityTurns() <= 0) {
            execute(user, targets, battle);
        } else {
            user.setLastMoveFailed(true);
        }
    }
}
