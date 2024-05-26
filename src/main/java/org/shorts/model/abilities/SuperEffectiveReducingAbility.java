package org.shorts.model.abilities;

public class SuperEffectiveReducingAbility extends Ability {
    private SuperEffectiveReducingAbility(String name){
        super(name);
    }

    public static final SuperEffectiveReducingAbility SOLID_ROCK = new SuperEffectiveReducingAbility("Solid Rock");
    public static final SuperEffectiveReducingAbility PRISM_ARMOR = new SuperEffectiveReducingAbility("Prism Armor");
    public static final SuperEffectiveReducingAbility FILTER = new SuperEffectiveReducingAbility("Filter");
}
