package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.MagicGuard.MAGIC_GUARD;
import static org.shorts.model.abilities.SheerForce.SHEER_FORCE;

public class LifeOrb extends HeldItem {

    private LifeOrb() {
        super("Life Orb");
    }

    public static final LifeOrb LIFE_ORB = new LifeOrb();

    @Override
    public void afterAttack(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (!(self.hasFainted() || (move.getSecondaryEffectChance() > 0 && self.getAbility() == SHEER_FORCE)
            || self.getAbility() == MAGIC_GUARD)) {
            self.takeDamage(self.getMaxHP() / 10);
        }
    }
}


