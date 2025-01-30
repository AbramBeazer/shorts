package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.Main.RANDOM;
import static org.shorts.model.abilities.SereneGrace.SERENE_GRACE;
import static org.shorts.model.abilities.SheerForce.SHEER_FORCE;
import static org.shorts.model.status.VolatileStatusType.FLINCH;

public class TripleArrows extends Move implements HighCritChanceMove, GetsSheerForceBoost {

    private static final int DEFENSE_DROP_CHANCE = 50;
    private static final int FLINCH_CHANCE = 30;

    public TripleArrows() {
        super("Triple Arrows", 90, 100, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 16, false, 50);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (user.getAbility() != SHEER_FORCE) {
            if (target.isDropPossible(StatEnum.DEF) && RANDOM.nextInt(100) < DEFENSE_DROP_CHANCE * (user.getAbility()
                .equals(SERENE_GRACE) ? 2 : 1)) {
                target.changeStat(-1, StatEnum.DEF);
            }
            if (RANDOM.nextInt(100) < FLINCH_CHANCE * (user.getAbility().equals(SERENE_GRACE) ? 2 : 1)
                && FLINCH.isStatusPossible(user, target, battle)) {
                target.addVolatileStatus(new VolatileStatus(FLINCH, 1));
            }
        }
    }
}
