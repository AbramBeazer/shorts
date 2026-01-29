package org.shorts.model.moves;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Sturdy;
import org.shorts.model.items.FocusSash;
import org.shorts.model.pokemon.Pokemon;

public class HurtItselfInConfusion extends Move {

    private HurtItselfInConfusion() {
        super("Hurt Itself", 40, -1, null, Category.PHYSICAL, Range.SELF, 1, false, 0);
    }

    @Override
    protected int applyMultipliers(Pokemon user, Pokemon target, Battle battle, double baseDamage) {
        return (int) (baseDamage * getRandomMultiplier());
    }

    @Override
    public boolean isDisabled() {
        return false;
    }

    @Override
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        executeOnTarget(user, targets.get(0), battle);
        if (user.hasFainted()) {
            user.afterFaint(battle);
        }
    }

    @Override
    protected void executeOnTarget(Pokemon user, Pokemon target, Battle battle) {
        doHit(user, target, battle);
    }

    @Override
    protected void doHit(Pokemon user, Pokemon target, Battle battle) {
        final int previousTargetHP = target.getCurrentHP();

        int damage = calculateDamage(user, target, battle);
        if (damage <= 0) {
            throw new RuntimeException("Damage cannot be zero or negative!");
        }
        target.takeDamage(damage);
        if (target.getAbility() == Sturdy.STURDY) {
            Sturdy.STURDY.afterHit(user, target, battle, previousTargetHP, this);
        }
        //TODO: Do this for Focus Band too.
        if (target.getHeldItem() == FocusSash.FOCUS_SASH) {
            FocusSash.FOCUS_SASH.afterHit(user, target, battle, previousTargetHP, this);
        }
    }

    @Override
    protected void decrementPP() {
        //Since this is a singleton, the PP should never decrement.
    }

    public static final HurtItselfInConfusion HURT_ITSELF_IN_CONFUSION = new HurtItselfInConfusion();
}
