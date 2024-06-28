package org.shorts.model.moves.screenremoving;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.battle.Trainer;
import org.shorts.model.StatEnum;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class Defog extends Move {

    public Defog() {
        super("Defog", 0, -1, Type.FLYING, Category.STATUS, Range.SINGLE_ADJACENT_ANY, 24, false, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (!target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)
            && !target.hasVolatileStatus(VolatileStatusType.SEMI_INVULNERABLE)
            && target.isDropPossible(StatEnum.EVASION)) {
            target.changeStat(-1, StatEnum.EVASION);
        }
        //        if(battle.getWeather() == Weather.FOG){
        //            battle.setWeather(Weather.NONE);
        //        }
        Trainer trainer = battle.getCorrespondingTrainer(target);
        trainer.setLightScreenTurns(0);
        trainer.setReflectTurns(0);
        trainer.setAuroraVeilTurns(0);
        trainer.setSafeguardTurns(0);
        trainer.setMistTurns(0);

        battle.getPlayerOne().removeEntryHazards();
        battle.getPlayerTwo().removeEntryHazards();

        battle.setTerrain(Terrain.NONE, -1);
    }
}
