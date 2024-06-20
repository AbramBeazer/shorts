package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;

public class NeutralizingGas extends Ability {

    private NeutralizingGas() {
        super("Neutralizing Gas");
    }

    public static final NeutralizingGas NEUTRALIZING_GAS = new NeutralizingGas();

    @Override
    public void afterEntry(Pokemon self, Battle battle) {
        System.out.println("Neutralizing Gas neutralizes other abilities!");
        for (Pokemon opp : battle.getOpposingActivePokemon(self)) {
            if (!(opp.getAbility() instanceof UnsuppressableAbility)) {
                opp.addVolatileStatus(VolatileStatus.ABILITY_SUPPRESSED);
            }
        }
        for (Pokemon ally : battle.getCorrespondingTrainer(self).getActivePokemon()) {
            if (ally != self && !(ally.getAbility() instanceof UnsuppressableAbility)) {
                ally.addVolatileStatus(VolatileStatus.ABILITY_SUPPRESSED);
            }
        }
    }
}
