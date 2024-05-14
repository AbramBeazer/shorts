package org.shorts.model.moves.recoil;

import java.util.Set;
import java.util.stream.Collectors;

import org.shorts.model.moves.PhysicalMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.TooManyTypesException;
import org.shorts.model.types.Type;

public class Struggle extends PhysicalMove implements RecoilAttack {

    private Struggle() {
        super("Struggle", 50, -1, Type.NORMAL, 1, true, 100);
    }

    @Override
    protected double getTypeMultiplier(Set<Type> defenderTypes) throws TooManyTypesException {
        Set<Type> modifiedDefenderTypes = defenderTypes.stream()
            .filter(type -> !type.equals(Type.GHOST))
            .collect(Collectors.toSet());
        return super.getTypeMultiplier(modifiedDefenderTypes);
    }

    @Override
    public void inflictRecoil(Pokemon user, int damageDealt) {
        RecoilAttack.super.inflictRecoil(user, user.getMaxHP());
    }

    @Override
    public double getRecoilPercentage() {
        return 0.25;
    }

    public static final Struggle STRUGGLE = new Struggle();
}
