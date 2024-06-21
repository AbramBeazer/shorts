package org.shorts.model.items;

import org.shorts.model.pokemon.Pokemon;

public class LightBall extends HeldItem {

    private LightBall() {
        super("Light Ball", 30);
    }

    public static final LightBall LIGHT_BALL = new LightBall();

    @Override
    public double onCalculateAttack(Pokemon self) {
        return self.getPokedexEntry().getPokedexNo() == 25 ? 2 : 1;
    }

    @Override
    public double onCalculateSpecialAttack(Pokemon self) {
        return self.getPokedexEntry().getPokedexNo() == 25 ? 2 : 1;
    }
}
