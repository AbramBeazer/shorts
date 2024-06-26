package org.shorts.model.moves;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class BurnUp extends Move implements SelfThawingMove {

    public BurnUp() {
        super("Burn Up", 130, 100, Type.FIRE, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 8, false, 0);
    }

    @Override
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        if (user.getTypes().contains(Type.FIRE)) {
            super.execute(user, targets, battle);
            //TODO: I'm pretty sure the removal of the Fire typing happens after the target's ability activates, meaning FlameBody shouldn't burn an attacker that uses Burn Up. I should verify this, though.
            //TODO: A terastallized mon with a Fire Tera type will not lose its fire typing.
            user.getTypes().remove(Type.FIRE);
        } else {
            System.out.println("But it failed!");
        }
    }

}
