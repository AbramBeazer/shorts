package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.StatusImmuneAbility;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.battle.Terrain.ELECTRIC;
import static org.shorts.battle.Terrain.MISTY;
import static org.shorts.battle.Weather.EXTREME_SUN;
import static org.shorts.battle.Weather.SUN;
import static org.shorts.model.abilities.Comatose.COMATOSE;
import static org.shorts.model.abilities.LeafGuard.LEAF_GUARD;

public class Rest extends Move {

    private static final int TURNS_OF_SLEEP = 3; // This is 3 including the turn that Rest is used, so the user will be asleep for the next two turns after Rest is used.

    public Rest() {
        super("Rest", 0, -1, Type.PSYCHIC, Category.STATUS, Range.SELF, 5, true, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (user.getCurrentHP() < user.getMaxHP() && !user.getAbility().equals(COMATOSE)
            && !(user.getAbility() instanceof StatusImmuneAbility
            && ((StatusImmuneAbility) user.getAbility()).getImmunities().contains(StatusType.SLEEP))
            && !target.hasVolatileStatus(VolatileStatusType.MAKING_AN_UPROAR)
            && !((battle.getWeather() == SUN || battle.getWeather() == EXTREME_SUN) && user.getAbility()
            .equals(LEAF_GUARD))
            && !(user.isGrounded() && (battle.getTerrain() == MISTY || battle.getTerrain() == ELECTRIC))) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.setStatus(Status.createSleepForTurns(TURNS_OF_SLEEP));
        user.setCurrentHP(user.getMaxHP());
        System.out.println(String.format("%s went to sleep and restored HP!", user));
    }
}
