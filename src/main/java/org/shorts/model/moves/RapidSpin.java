package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.volatilestatus.VolatileStatus;
import org.shorts.model.types.Type;

public class RapidSpin extends PhysicalMove {

    public RapidSpin() {
        super("Rapid Spin", 50, 100, Type.NORMAL, 64, true, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        attacker.changeSpeed(1);
        Trainer trainer = battle.getPlayerOne().getLead() == attacker ? battle.getPlayerOne() : battle.getPlayerTwo();
        trainer.removeEntryHazards();
        attacker.getVolatileStatuses()
            .removeIf(vs -> vs.getType().equals(VolatileStatus.VolatileStatusType.SEEDED) || vs.getType().equals(
                VolatileStatus.VolatileStatusType.BOUND));
    }
}
