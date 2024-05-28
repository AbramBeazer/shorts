package org.shorts.model.moves.recoil;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

public class VoltTackle extends RecoilAttack {

    public VoltTackle() {
        super(
            "Volt Tackle",
            120,
            100,
            Type.ELECTRIC,
            Category.PHYSICAL,
            Range.SINGLE_ADJACENT_ANY,
            24,
            true,
            10,
            1 / 3d);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (Status.PARALYZE.isStatusPossible(defender, battle)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.setStatus(Status.PARALYZE);
    }
}
