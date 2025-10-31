package org.shorts.model.moves.priority.plusOne;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.PunchingMove;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class MachPunch extends Move implements PunchingMove {

    public MachPunch() {
        super("Mach Punch", 40, 100, Type.FIGHTING, Move.Category.PHYSICAL, Range.NORMAL, 48, true, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        return 1;
    }
}
