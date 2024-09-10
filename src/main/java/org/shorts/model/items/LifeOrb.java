package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.MagicGuard.MAGIC_GUARD;
import static org.shorts.model.abilities.SheerForce.SHEER_FORCE;

public class LifeOrb extends HeldItem {

    public static final double LIFE_ORB_MULTIPLIER = 1.3;

    private LifeOrb() {
        super("Life Orb", 30);
    }

    public static final LifeOrb LIFE_ORB = new LifeOrb();

    @Override
    public void afterAttack(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        if (!(user.hasFainted() || (move instanceof GetsSheerForceBoost && user.getAbility() == SHEER_FORCE)
            || user.getAbility() == MAGIC_GUARD)) {
            user.takeDamage(user.getMaxHP() / 10);
        }
    }
}


