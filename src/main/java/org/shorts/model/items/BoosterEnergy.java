package org.shorts.model.items;

public class BoosterEnergy extends HeldItem {

    private BoosterEnergy() {
        super("Booster Energy", 30);
    }

    public static final BoosterEnergy BOOSTER_ENERGY = new BoosterEnergy();
}
