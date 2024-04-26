package org.shorts.model.items;

import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.Activation.OTHER;

public class HeavyDutyBoots extends HeldItem {

    public HeavyDutyBoots() {
        super("Heavy-Duty Boots", OTHER);
    }

    public static final HeavyDutyBoots HEAVY_DUTY_BOOTS = new HeavyDutyBoots();

    @Override
    public void activate(Pokemon self, Pokemon opponent) {
        //keep empty
    }
}
