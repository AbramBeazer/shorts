package org.shorts.model.moves.recoil;

import java.util.Set;

import org.shorts.MathUtils;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.TooManyTypesException;
import org.shorts.model.types.Type;

public class Struggle extends RecoilAttack {

    private Struggle() {
        super("Struggle", 50, -1, Type.NORMAL, Category.PHYSICAL, Range.RANDOM_OPPONENT, 1, true, 0, 0.25);
    }

    @Override
    protected double getSTABMultiplier(Set<Type> attackerTypes) throws TooManyTypesException {
        return 1;
    }

    @Override
    protected double getBaseTypeMultiplier(Set<Type> defenderTypes) throws TooManyTypesException {
        return 1;
    }

    @Override
    public void inflictRecoil(Pokemon user, int damageDealt) {
        user.takeDamage(
            (int) MathUtils.roundHalfUp(user.getMaxHP() * this.recoilPercentage),
            String.format("%s was damaged by the recoil!", user.toString()));
    }

    public static final Struggle STRUGGLE = new Struggle();
}
