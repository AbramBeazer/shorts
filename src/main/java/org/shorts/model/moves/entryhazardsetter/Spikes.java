package org.shorts.model.moves.entryhazardsetter;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Spikes extends Move implements EntryHazardSetter {

    public Spikes() {
        super("Spikes", 0, -1, Type.GROUND, Category.STATUS, Range.OTHER_SIDE, 32, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        Trainer targetSide = battle.getCorrespondingTrainer(target);
        if (targetSide.getSpikes() < 3) {
            targetSide.addSpikes();
            System.out.println("Spikes were scattered around " + targetSide.getName() + "'s team!");
        }
    }
}
