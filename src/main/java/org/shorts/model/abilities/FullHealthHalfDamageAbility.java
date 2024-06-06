package org.shorts.model.abilities;

public class FullHealthHalfDamageAbility extends Ability {

    private FullHealthHalfDamageAbility(String name){
        super(name);
    }

    public static final FullHealthHalfDamageAbility MULTISCALE = new FullHealthHalfDamageAbility("Multiscale");
    public static final FullHealthHalfDamageAbility SHADOW_SHIELD = new FullHealthHalfDamageAbility("Shadow Shield");
}
