package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Guts.GUTS;
import static org.shorts.model.abilities.SheerForce.SHEER_FORCE;

public abstract class PhysicalMove extends Move {

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!attacker.getAbility().equals(SHEER_FORCE)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    protected PhysicalMove(
        String name, double power, double accuracy, Type type, int maxPP, boolean contact, int secondaryEffectChance) {
        super(name, power, accuracy, type, maxPP, contact, secondaryEffectChance);
    }

    @Override
    protected int getAttackingStat(Pokemon user, Pokemon target) {
        return user.calculateAttack();
    }

    @Override
    protected int getDefendingStat(Pokemon user, Pokemon target) {
        return user.calculateDefense();
    }

    @Override
    protected double getBurnMultiplier(Pokemon user) {
        if (user.getStatus() == Status.BURN && !user.getAbility().equals(GUTS)) {
            return 0.5;
        } else {
            return 1;
        }
    }
}
