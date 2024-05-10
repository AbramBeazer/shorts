package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.StatusImmuneAbility;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.volatilestatus.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.battle.Terrain.ELECTRIC;
import static org.shorts.battle.Terrain.MISTY;
import static org.shorts.battle.Weather.EXTREME_SUN;
import static org.shorts.battle.Weather.SUN;
import static org.shorts.model.abilities.Comatose.COMATOSE;
import static org.shorts.model.abilities.LeafGuard.LEAF_GUARD;

public class Rest extends StatusMove {

    private static final int TURNS_OF_SLEEP = 3; // This is 3 including the turn that Rest is used, so the user will be asleep for the next two turns after Rest is used.

    public Rest() {
        super("Rest", -1, Type.PSYCHIC, 5, true);
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (attacker.getCurrentHP() < attacker.getMaxHP() && !attacker.getAbility().equals(COMATOSE) && !(
            attacker.getAbility() instanceof StatusImmuneAbility
                && ((StatusImmuneAbility) attacker.getAbility()).getImmunities().contains(Status.StatusType.SLEEP))
            && defender.getVolatileStatuses()
            .stream()
            .noneMatch(vs -> vs.getType().equals(VolatileStatus.VolatileStatusType.MAKING_AN_UPROAR)) && !(
            (battle.getWeather() == SUN || battle.getWeather() == EXTREME_SUN) && attacker.getAbility()
                .equals(LEAF_GUARD)) && !(attacker.isGrounded() && (battle.getTerrain() == MISTY
            || battle.getTerrain() == ELECTRIC))) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        attacker.setStatus(Status.createSleepForTurns(TURNS_OF_SLEEP));
        attacker.setCurrentHP(attacker.getMaxHP());
    }
}
