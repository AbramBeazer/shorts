package org.shorts.model.abilities;

public class LightMetal extends Ability {

    private LightMetal() {
        super("Light Metal");
    }

    public static final LightMetal LIGHT_METAL = new LightMetal();

    @Override
    public double getWeightMultiplier() {
        return .5;
    }
}
