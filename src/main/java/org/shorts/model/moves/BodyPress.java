package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class BodyPress extends Move {

    public BodyPress() {
        super("Body Press", 80, 100, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 16, true, 0);
    }

    @Override
    protected double getAttackingStat(Pokemon attacker, Pokemon defender, Battle battle) {
        return attacker.calculateDefense(battle);
    }
}
