package org.shorts.model.abilities.elementabsorb;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Ability;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.types.Type.FIRE;

public class FlashFire extends Ability {

    private boolean activated = false;
    public static final double MULTIPLIER = 1.5;

    public FlashFire() {
        super("Flash Fire");
    }

    @Override
    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (move.getType() == FIRE) {
            activated = true;
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public double getAttackMultipliers(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (activated && move.getType() == FIRE) {
            return MULTIPLIER;
        } else {
            return 1;
        }
    }

    @Override
    public void beforeSwitchOut(Pokemon self, Pokemon opponent, Battle battle) {
        activated = false;
    }

    protected boolean isActivated() {
        return this.activated;
    }
}
