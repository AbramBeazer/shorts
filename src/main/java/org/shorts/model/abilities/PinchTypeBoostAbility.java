package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class PinchTypeBoostAbility extends Ability {

    private final Type type;

    PinchTypeBoostAbility(String name, Type type) {
        super(name);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public double beforeAttack(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (self.getCurrentHP() <= self.getMaxHP() / 3 && move.getType().equals(this.type)) {
            return 1.5;
        } else {
            return 1;
        }
    }

    public static final PinchTypeBoostAbility OVERGROW = new PinchTypeBoostAbility("Overgrow", Type.GRASS);
    public static final PinchTypeBoostAbility BLAZE = new PinchTypeBoostAbility("Blaze", Type.FIRE);
    public static final PinchTypeBoostAbility TORRENT = new PinchTypeBoostAbility("Torrent", Type.WATER);
    public static final PinchTypeBoostAbility SWARM = new PinchTypeBoostAbility("Swarm", Type.BUG);
}
