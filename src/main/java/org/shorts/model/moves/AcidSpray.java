package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.IgnorableAbility;
import org.shorts.model.abilities.StatPreservingAbility;
import org.shorts.model.abilities.StatPreservingIgnorableAbility;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class AcidSpray extends Move implements BallBombMove {

    public AcidSpray() {
        super("Acid Spray", 40, 100, Type.POISON, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 32, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if(){
            //TODO: Output message about ability blocking stat drop.
        }
        else{
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeSpecialDefense(-2);
    }
}
