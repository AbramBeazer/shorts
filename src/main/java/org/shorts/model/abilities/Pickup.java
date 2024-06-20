package org.shorts.model.abilities;

import java.util.ArrayList;
import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.items.NoItem.NO_ITEM;

public class Pickup extends Ability {

    private static List<Pokemon> consumedItemsThisTurn = new ArrayList<>(6);

    private Pickup() {
        super("Pickup");
    }

    public static final Pickup PICKUP = new Pickup();

    @Override
    public void beforeTurn(Pokemon self, Pokemon opponent, Battle battle) {
        if (!consumedItemsThisTurn.isEmpty()) {
            consumedItemsThisTurn = new ArrayList<>(6);
        }
    }

    public static void addToConsumedItems(Pokemon pokemon) {
        consumedItemsThisTurn.remove(pokemon);
        consumedItemsThisTurn.add(pokemon);
    }

    public static void removeFromConsumedItems(Pokemon pokemon) {
        consumedItemsThisTurn.remove(pokemon);
    }

    @Override
    public void afterTurn(Pokemon self, Battle battle) {
        if (self.getHeldItem() == NO_ITEM && !consumedItemsThisTurn.isEmpty()) {
            int selfIndex = consumedItemsThisTurn.indexOf(self);
            int index = consumedItemsThisTurn.size() - 1;
            while (index == selfIndex) {
                index--;
            }
            if (index >= 0) {
                final Pokemon pokemon = consumedItemsThisTurn.remove(index);
                self.setHeldItem(pokemon.getConsumedItem());
                pokemon.setConsumedItem(NO_ITEM);
            }
        }
    }
}
