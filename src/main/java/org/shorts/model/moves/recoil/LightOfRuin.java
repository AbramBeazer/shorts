package org.shorts.model.moves.recoil;

import org.shorts.model.moves.SpecialMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class LightOfRuin extends SpecialMove implements RecoilAttack {

    //Note that this move doesn't exist from Gen 8 onward.

    public LightOfRuin() {
        super("Light of Ruin", 140, 90, Type.FAIRY, 8, false, 0);
    }

    @Override
    public double getRecoilPercentage() {
        return 0.5;
    }

    @Override
    public void inflictRecoil(Pokemon user, int damageDealt) {
        RecoilAttack.super.inflictRecoil(user, damageDealt);
    }
}
