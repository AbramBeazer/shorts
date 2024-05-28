package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class Ingrain extends Move {

    public Ingrain() {
        super("Ingrain", 0, -1, Type.GRASS, Category.STATUS, Range.SELF, 32, false, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        attacker.addVolatileStatus(new VolatileStatus(VolatileStatusType.ROOTED, -1));
    }
}
