package org.shorts.model.moves.priority.plusOne;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class VacuumWave extends Move {

    public VacuumWave() {
        super("Vacuum Wave", 40, 100, Type.FIGHTING, Category.SPECIAL, Range.NORMAL, 48, false, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        return 1;
    }
}
