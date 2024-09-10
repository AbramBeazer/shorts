package org.shorts.model.abilities;

public class HeavyMetal extends Ability implements IgnorableAbility {

    private HeavyMetal() {
        super("Heavy Metal");
    }

    public static final HeavyMetal HEAVY_METAL = new HeavyMetal();

    @Override
    public double getWeightMultiplier() {
        return 2;
    }
}
