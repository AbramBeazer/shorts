package org.shorts.model.moves.recoil;

import org.shorts.model.moves.SpecialMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Chloroblast extends SpecialMove implements RecoilAttack {

    public Chloroblast() {
        super("Chlorolast", 150, 95, Type.GRASS, 8, false, 0);
    }

    @Override
    public double getRecoilPercentage() {
        return 0.5;
    }

    @Override
    public void inflictRecoil(Pokemon user, int damageDealt) {
        RecoilAttack.super.inflictRecoil(user, user.getMaxHP());
    }
}
