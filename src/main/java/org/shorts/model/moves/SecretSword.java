package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class SecretSword extends Move implements SlicingMove {

    public SecretSword() {
        super("Secret Sword", 85, 100, Type.FIGHTING, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 16, false, 0);
    }

    @Override
    protected double getDefendingStat(Pokemon attacker, Pokemon defender, Battle battle) {
        return defender.calculateDefense(battle);
    }
}
