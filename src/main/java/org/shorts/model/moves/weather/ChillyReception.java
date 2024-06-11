package org.shorts.model.moves.weather;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.battle.Weather;
import org.shorts.model.moves.switchself.SwitchSelfMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class ChillyReception extends WeatherMove implements SwitchSelfMove {

    public ChillyReception() {
        super("Chilly Reception", Weather.SNOW, Type.ICE, 16);
    }

    //NOTE: This does not override trySecondaryEffect because it should still create SNOW even if the user can't switch.
    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        super.applySecondaryEffect(user, target, battle);
        final Trainer trainer =
            battle.getPlayerOne().getLead() == user ? battle.getPlayerOne() : battle.getPlayerTwo();

        battle.promptSwitchCausedByUserMove(trainer);
        //TODO: Remove this
    }
}
