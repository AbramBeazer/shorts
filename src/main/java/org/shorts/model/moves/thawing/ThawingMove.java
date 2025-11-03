package org.shorts.model.moves.thawing;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public abstract class ThawingMove extends Move {

    protected ThawingMove(
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
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        user.thaw();
        super.execute(user, targets, battle);
    }

    @Override
    public boolean isThawingMove() {
        return true;
    }
}
