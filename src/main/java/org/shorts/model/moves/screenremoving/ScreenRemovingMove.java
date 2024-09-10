package org.shorts.model.moves.screenremoving;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public abstract class ScreenRemovingMove extends Move {

    protected ScreenRemovingMove(
        String name,
        double power,
        double accuracy,
        Type type,
        Category category,
        Range range,
        int maxPP,
        boolean contact,
        int secondaryEffectChance) {
        super(name, power, accuracy, type, category, range, maxPP, contact, secondaryEffectChance);
    }

    @Override
    protected double getTypeMultiplier(Pokemon user, Pokemon target, Battle battle) {
        double multiplier = super.getTypeMultiplier(user, target, battle);
        if (multiplier > Type.IMMUNE) {
            Trainer trainer = battle.getCorrespondingTrainer(target);
            trainer.setLightScreenTurns(0);
            trainer.setReflectTurns(0);
            trainer.setAuroraVeilTurns(0);
        }
        return multiplier;
    }
}
