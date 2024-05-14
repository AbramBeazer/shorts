package org.shorts.model.moves.recoil;

import org.shorts.model.moves.PhysicalMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public abstract class DamageDealtPhysicalRecoilAttack extends PhysicalMove implements RecoilAttack {

    private final double recoilPercentage;

    protected DamageDealtPhysicalRecoilAttack(
        String name,
        double power,
        double accuracy,
        Type type,
        int maxPP,
        boolean contact,
        int secondaryEffectChance,
        double recoilPercentage) {
        super(name, power, accuracy, type, maxPP, contact, secondaryEffectChance);
        this.recoilPercentage = recoilPercentage;
    }

    @Override
    public double getRecoilPercentage() {
        return this.recoilPercentage;
    }

    @Override
    public void inflictRecoil(Pokemon user, int damageDealt) {
        RecoilAttack.super.inflictRecoil(user, damageDealt);
    }
}
