package org.shorts.model.moves.healthdependent;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public abstract class HealthDependentMove extends Move {

    static final int MAX_POWER = 150;

    protected HealthDependentMove(
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
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        return Math.max(1, MAX_POWER * user.getCurrentHP() / user.getMaxHP());
    }
}
