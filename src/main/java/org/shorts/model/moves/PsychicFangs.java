package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class PsychicFangs extends Move implements BitingMove {

    public PsychicFangs() {
        super("Psychic Fangs", 85, 100, Type.PSYCHIC, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 0);
    }

    @Override
    protected double getTypeMultiplier(Pokemon user, Pokemon target, Battle battle) {
        double multiplier = super.getTypeMultiplier(user, target, battle);
        if (multiplier > Type.IMMUNE) {
            Trainer opponent = battle.getCorrespondingTrainer(target);
            opponent.setLightScreenTurns(0);
            opponent.setReflectTurns(0);
            opponent.setAuroraVeilTurns(0);
        }
        return multiplier;
    }
}
