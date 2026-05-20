package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Howl extends Move implements SoundEffect {

    public Howl() {
        super("Howl", 0, -1, Type.NORMAL, Category.STATUS, Range.OWN_PARTY, 64, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (affectsTarget(user, target)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(battle, user, 1, StatEnum.ATK);
    }

    protected boolean affectsTarget(Pokemon user, Pokemon target) {
        if (user != target && SoundEffect.super.soundproofApplies(target)) {
            return false;
        }
        return target.canChangeStat(1, StatEnum.ATK);
    }
}
