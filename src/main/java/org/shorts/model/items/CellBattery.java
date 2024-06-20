package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.abilities.elementabsorb.ElementAbsorbRaiseStatAbility;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.items.NoItem.NO_ITEM;

public class CellBattery extends HeldItem {

    private CellBattery() {
        super("Cell Battery", 30);
    }

    public static final CellBattery CELL_BATTERY = new CellBattery();

    @Override
    public void afterHit(Pokemon user, Pokemon opponent, Battle battle, int previousHP, Move move) {
        if (move.getType().equals(Type.ELECTRIC) && !user.getTypes().contains(Type.GROUND)
            && !(user.getAbility() instanceof ElementAbsorbRaiseStatAbility) && user.canChangeStat(1, StatEnum.ATK)) {
            user.changeAttack(1);
            user.setConsumedItem(user.getHeldItem());
            user.setHeldItem(NO_ITEM);
        }
    }
}
