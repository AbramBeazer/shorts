package org.shorts.model.abilities;

import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.PowderSporeEffect;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;

public class EffectSpore extends Ability implements PowderSporeEffect {

    private EffectSpore() {
        super("Effect Spore");
    }

    @Override
    public void afterHit(Pokemon self, Pokemon opponent, Battle battle, int previousHP, Move move) {
        if (move.isContact(opponent) && this.asPowderSporeEffectData().canActivate(opponent)) {
            final int chance = Main.RANDOM.nextInt(30);
            Status status; //9% chance of poison, 10% chance of paralyze, 11% chance of sleep
            if (chance < 9) {
                status = Status.POISON;
            } else if (chance >= 19) {
                status = Status.createSleep();
            } else {
                status = Status.PARALYZE;
            }
            if (status.getType().isStatusPossible(self, opponent, battle)) {
                opponent.setStatus(status);
            }
        }
    }
}
