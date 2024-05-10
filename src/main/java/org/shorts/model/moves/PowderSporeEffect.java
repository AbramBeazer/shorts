package org.shorts.model.moves;

public interface PowderSporeEffect {

    default PowderSporeEffectData asPowderSporeEffectData() {
        return new PowderSporeEffectData(this);
    }
}
