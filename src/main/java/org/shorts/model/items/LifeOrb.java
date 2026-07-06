package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.MagicGuard.*;
import static org.shorts.model.abilities.SheerForce.*;

public class LifeOrb extends HeldItem {

    public static final double LIFE_ORB_MULTIPLIER = 5324d / Move.COMMON_DIVISOR;

    private LifeOrb() {
        super("Life Orb", 30);
    }

    public static final LifeOrb LIFE_ORB = new LifeOrb();

    @Override
    public void afterAttack(Pokemon user, Battle battle, Move move) {
        if (move.getCategory() != Move.Category.STATUS && !user.isLastMoveFailed()
            && !(user.hasFainted() || (move.getsSheerForceBoost() && user.getAbility() == SHEER_FORCE)
            || user.getAbility() == MAGIC_GUARD)) {
            user.takeDamage(
                user.getMaxHP() / 10,
                String.format("%s took damage from the Life Orb!", user));
        }
    }
}


