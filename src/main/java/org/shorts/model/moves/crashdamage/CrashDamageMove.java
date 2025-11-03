package org.shorts.model.moves.crashdamage;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public abstract class CrashDamageMove extends Move {

    protected CrashDamageMove(
        String name,
        double power,
        double accuracy,
        Type type,
        int maxPP,
        int secondaryEffectChance) {
        super(name, power, accuracy, type, Category.PHYSICAL, Range.NORMAL, maxPP, true, secondaryEffectChance);
    }

    @Override
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        if (battle.getGravityTurns() <= 0) {
            execute(user, targets, battle);
        }
    }

    @Override
    protected void executeOnTarget(Pokemon user, Pokemon target, Battle battle) {
        if (rollToHit(user, target, battle) && !isTargetProtected(user, target, battle)) {
            doHit(user, target, battle);
        } else {
            crash(user);
        }
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
