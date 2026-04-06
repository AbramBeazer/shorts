package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class TeraBlast extends Move {

    protected static final int BASE_POWER = 80;
    protected static final int STELLAR_POWER = 100;

    public TeraBlast() {
        super("Tera Blast", BASE_POWER, 100, Type.NORMAL, Category.SPECIAL, Range.NORMAL, 16, false, 100);
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        return user.isTera() && user.getTeraType() instanceof Type.StellarType ? STELLAR_POWER : BASE_POWER;
    }

    @Override
    protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
        if (user.isTera()
            && user.calculateAttackWithoutAbilityOrItem() > user.calculateSpecialAttackWithoutAbilityOrItem()) {
            setCategory(Category.PHYSICAL);
        } else {
            setCategory(Category.SPECIAL);
        }
        return super.calculateDamage(user, target, battle);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (getType() instanceof Type.StellarType) {
            user.changeStat(-1, StatEnum.ATK, battle, user);
            user.changeStat(-1, StatEnum.SPATK, battle, user);
        }
    }
}
