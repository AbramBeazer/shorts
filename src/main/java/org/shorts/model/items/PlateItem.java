package org.shorts.model.items;

import org.shorts.model.types.Type;

public class PlateItem extends HeldItem {

    private final Type type;

    private PlateItem(String name, Type type) {
        super(name + " Plate");
        this.type = type;
    }

    public static final PlateItem BLANK_PLATE = new PlateItem("Blank", Type.NORMAL);
    public static final PlateItem FIST_PLATE = new PlateItem("Fist", Type.FIGHTING);
    public static final PlateItem SKY_PLATE = new PlateItem("Sky", Type.FLYING);
    public static final PlateItem TOXIC_PLATE = new PlateItem("Toxic", Type.POISON);
    public static final PlateItem EARTH_PLATE = new PlateItem("Earth", Type.GROUND);
    public static final PlateItem STONE_PLATE = new PlateItem("Stone", Type.ROCK);
    public static final PlateItem INSECT_PLATE = new PlateItem("Insect", Type.BUG);
    public static final PlateItem SPOOKY_PLATE = new PlateItem("Spooky", Type.GHOST);
    public static final PlateItem IRON_PLATE = new PlateItem("Iron", Type.STEEL);
    public static final PlateItem FLAME_PLATE = new PlateItem("Flame", Type.FIRE);
    public static final PlateItem SPLASH_PLATE = new PlateItem("Splash", Type.WATER);
    public static final PlateItem MEADOW_PLATE = new PlateItem("Meadow", Type.GRASS);
    public static final PlateItem ZAP_PLATE = new PlateItem("Zap", Type.ELECTRIC);
    public static final PlateItem MIND_PLATE = new PlateItem("Mind", Type.PSYCHIC);
    public static final PlateItem ICICLE_PLATE = new PlateItem("Icicle", Type.ICE);
    public static final PlateItem DRACO_PLATE = new PlateItem("Draco", Type.DRAGON);
    public static final PlateItem DREAD_PLATE = new PlateItem("Dread", Type.DARK);
    public static final PlateItem PIXIE_PLATE = new PlateItem("Pixie", Type.FAIRY);

}
