package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

public class ScreenCleaner extends Ability {

    private ScreenCleaner() {
        super("Screen Cleaner");
    }

    public static final ScreenCleaner SCREEN_CLEANER = new ScreenCleaner();

    @Override
    public void afterEntry(Pokemon self, Battle battle) {
        battle.getPlayerOne().setAuroraVeilTurns(0);
        battle.getPlayerOne().setReflectTurns(0);
        battle.getPlayerOne().setLightScreenTurns(0);
        
        battle.getPlayerTwo().setAuroraVeilTurns(0);
        battle.getPlayerTwo().setReflectTurns(0);
        battle.getPlayerTwo().setLightScreenTurns(0);
    }
}
