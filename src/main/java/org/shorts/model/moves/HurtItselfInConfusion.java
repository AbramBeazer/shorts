package org.shorts.model.moves;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Sturdy;
import org.shorts.model.items.FocusSash;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class HurtItselfInConfusion extends Move {

    private HurtItselfInConfusion() {
        super("Hurt Itself", 40, -1, Type.TYPELESS, Category.PHYSICAL, Range.SELF, 1, false, 0);
    }

    @Override
    protected int applyMultipliers(
        Pokemon user,
        Pokemon target,
        Battle battle,
        double baseDamage,
        double typeMultiplier) {
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
        if (user == target) {
            doHit(user, target, battle);
        }
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
    protected double calculateMovePower(Pokemon user, Pokemon target, Battle battle) {
        return this.getPower(user, target, battle);
    }

    @Override
    protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
        double movePower = calculateMovePower(user, target, battle);

        double attackingStat = getAttackingStat(user, target, battle);
        double defendingStat = getDefendingStat(user, target, battle);

        double baseDamage = ((0.4 * user.getLevel() + 2) * movePower * (attackingStat / defendingStat) * 0.02) + 2;
        double realDamage = applyMultipliers(user, target, battle, baseDamage, Type.NEUTRAL);
        return realDamage == 0 ? 0 : (int) Math.max(1, realDamage);
    }

    @Override
    protected double getAttackingStat(Pokemon attacker, Pokemon defender, Battle battle) {
        return attacker.calculateAttackWithoutAbilityOrItem();
    }

    @Override
    protected void decrementPP() {
        //Since this is a singleton, the PP should never decrement.
    }

    public static final HurtItselfInConfusion HURT_ITSELF_IN_CONFUSION = new HurtItselfInConfusion();
}
