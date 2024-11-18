package org.shorts.model.moves.trapping.binding;

import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.BoundStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.items.BindingBand.BINDING_BAND;
import static org.shorts.model.items.GripClaw.GRIP_CLAW;

public abstract class BindingMove extends Move {

    protected BindingMove(
        String name,
        double power,
        double accuracy,
        Type type,
        Category category,
        Range range,
        int maxPP,
        boolean contact,
        int secondaryEffectChance) {
        super(name, power, accuracy, type, category, range, maxPP, contact, secondaryEffectChance);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (VolatileStatusType.BOUND.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        final double damage = user.getHeldItem() == BINDING_BAND ? (1 / 6d) : 0.125;
        final int turns = user.getHeldItem() == GRIP_CLAW ? 7 : getDuration();
        target.addVolatileStatus(new BoundStatus(turns, this, damage));
    }

    private int getDuration() {
        final int randomNum = Main.RANDOM.nextInt(2);
        if (randomNum == 0) {
            return 4;
        } else {
            return 5;
        }
    }
}
