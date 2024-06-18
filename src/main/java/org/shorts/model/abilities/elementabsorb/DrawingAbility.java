package org.shorts.model.abilities.elementabsorb;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class DrawingAbility extends ElementAbsorbRaiseStatAbility {

    private DrawingAbility(String name, Type type, StatEnum stat) {
        super(name, type, stat);
    }

    @Override
    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        //TODO: Implement drawing move of certain type away.
        return super.beforeHit(self, opponent, battle, move);
    }

    public static final DrawingAbility STORM_DRAIN = new DrawingAbility("Storm Drain", Type.WATER, StatEnum.SPATK);
    public static final DrawingAbility LIGHTNING_ROD = new DrawingAbility(
        "Lightning Rod",
        Type.ELECTRIC,
        StatEnum.SPATK);
}
