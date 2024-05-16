package org.shorts.model.items;

import org.shorts.model.types.Type;

public class MemoryItem extends HeldItem {

    private final Type type;

    private MemoryItem(Type type) {
        super(type.getName() + " Memory");
        this.type = type;
    }

    public static final MemoryItem BUG_MEMORY = new MemoryItem(Type.BUG);
    public static final MemoryItem DARK_MEMORY = new MemoryItem(Type.DARK);
    public static final MemoryItem DRAGON_MEMORY = new MemoryItem(Type.DRAGON);
    public static final MemoryItem ELECTRIC_MEMORY = new MemoryItem(Type.ELECTRIC);
    public static final MemoryItem FAIRY_MEMORY = new MemoryItem(Type.FAIRY);
    public static final MemoryItem FIGHTING_MEMORY = new MemoryItem(Type.FIGHTING);
    public static final MemoryItem FIRE_MEMORY = new MemoryItem(Type.FIRE);
    public static final MemoryItem FLYING_MEMORY = new MemoryItem(Type.FLYING);
    public static final MemoryItem GHOST_MEMORY = new MemoryItem(Type.GHOST);
    public static final MemoryItem GRASS_MEMORY = new MemoryItem(Type.GRASS);
    public static final MemoryItem GROUND_MEMORY = new MemoryItem(Type.GROUND);
    public static final MemoryItem ICE_MEMORY = new MemoryItem(Type.ICE);
    public static final MemoryItem POISON_MEMORY = new MemoryItem(Type.POISON);
    public static final MemoryItem PSYCHIC_MEMORY = new MemoryItem(Type.PSYCHIC);
    public static final MemoryItem ROCK_MEMORY = new MemoryItem(Type.ROCK);
    public static final MemoryItem STEEL_MEMORY = new MemoryItem(Type.STEEL);
    public static final MemoryItem WATER_MEMORY = new MemoryItem(Type.WATER);
}
