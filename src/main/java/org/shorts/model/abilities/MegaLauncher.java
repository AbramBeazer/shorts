package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.PulseEffect;
import org.shorts.model.pokemon.Pokemon;

public class MegaLauncher extends Ability {

    protected static final double MULTIPLIER = 1.5;

    private MegaLauncher() {
        super("Mega Launcher");
    }

    public static final MegaLauncher MEGA_LAUNCHER = new MegaLauncher();

    @Override
    public double getMovePowerMultipliers(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return move instanceof PulseEffect ? MULTIPLIER : 1;
    }
}
