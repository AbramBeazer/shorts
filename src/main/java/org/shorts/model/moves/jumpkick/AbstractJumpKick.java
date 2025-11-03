package org.shorts.model.moves.jumpkick;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.moves.KickingMove;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public abstract class AbstractJumpKick extends Move implements KickingMove {

    protected AbstractJumpKick(String name, double power, double accuracy, int maxPP) {
        super(name, power, accuracy, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL, maxPP, true, 0);
    }

    @Override
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        if (battle.getGravityTurns() <= 0) {
            execute(user, targets, battle);
        }
    }

    @Override
    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        final boolean hits = super.rollToHit(user, target, battle);
        if (!hits) {
            crash(user);
        }
        return hits;
    }

    @Override
    protected int applyMultipliers(Pokemon user, Pokemon target, Battle battle, double baseDamage) {
        final int base = super.applyMultipliers(user, target, battle, baseDamage);
        if (base == 0) {
            crash(user);
        }
        return base;
    }

    private void crash(Pokemon user) {
        System.out.println(user + " kept going and crashed!");
        user.takeDamage(user.getMaxHP() / 2);
    }
}
