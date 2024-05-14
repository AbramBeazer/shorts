package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class Spore extends StatusMove implements PowderSporeEffect {

    public Spore() {
        super("Spore", 0, Type.GRASS, 24, false);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        //TODO: Make sure that this method gets called after we know that the move hits. I know this move always hits, but in general, I want the hit check to happen first.
        //TODO: Maybe the check for substitute and semi-invulnerable should be in the parent method.
        if (defender.hasVolatileStatus(SUBSTITUTE) || defender.hasVolatileStatus(SEMI_INVULNERABLE)
            || !this.asPowderSporeEffectData().canActivate(defender)) {
            System.out.println("It doesn't affect " + defender.getNickname());
        } else {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.setStatus(Status.createSleep());
    }

}
