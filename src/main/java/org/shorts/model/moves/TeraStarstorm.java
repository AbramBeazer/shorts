package org.shorts.model.moves;

import java.util.List;
import java.util.stream.Collectors;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class TeraStarstorm extends Move {

    public TeraStarstorm() {
        super("Tera Starstorm", 120, 100, Type.NORMAL, Category.SPECIAL, Range.NORMAL, 8, false, 100);
    }

    @Override
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        if (user.isTera()) {
            //Duplicates logic from Turn.takeTurn()
            targets = battle.getPokemonWithinRange(user, Range.ALL_ADJACENT_OPPONENTS)
                .stream()
                .filter(poke -> !poke.hasFainted())
                .collect(Collectors.toList());
        }
        super.execute(user, targets, battle);
    }

    @Override
    protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
        if (user.isTera()
            && user.calculateAttackWithoutAbilityOrItem() > user.calculateSpecialAttackWithoutAbilityOrItem()) {
            setCategory(Category.PHYSICAL);
        } else {
            setCategory(Category.SPECIAL);
        }
        return super.calculateDamage(user, target, battle);
    }
}
