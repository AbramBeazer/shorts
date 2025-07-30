package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.WindPower.*;
import static org.shorts.model.abilities.WindRider.*;

public class Tailwind extends Move implements WindMove {

    static final int BASE_TURNS = 4;

    public Tailwind() {
        super("Tailwind", 0, -1, Type.FLYING, Category.STATUS, Range.OWN_SIDE, 24, false, 0);
    }

    @Override
    protected void executeOnSide(Pokemon user, Battle battle) {
        final Trainer trainer = battle.getCorrespondingTrainer(user);
        battle.getCorrespondingTrainer(user).setTailwindTurns(BASE_TURNS);
        for (Pokemon pokemon : trainer.getActivePokemon()) {
            if (!pokemon.hasFainted()) {
                if (pokemon.getAbility() == WIND_POWER) {
                    pokemon.getAbility().afterHit(pokemon, user, battle, pokemon.getCurrentHP(), this);
                } else if (pokemon.getAbility() == WIND_RIDER) {
                    pokemon.getAbility().beforeHit(pokemon, user, battle, this);
                }
            }
        }
    }
}
