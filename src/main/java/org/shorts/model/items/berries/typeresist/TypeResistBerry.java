package org.shorts.model.items.berries.typeresist;

import org.shorts.model.items.berries.Berry;
import org.shorts.model.types.Type;

public class TypeResistBerry extends Berry {

    private final Type type;

    public TypeResistBerry(String name, Type type) {
        super(name);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public static final TypeResistBerry OCCA_BERRY = new TypeResistBerry("Occa", Type.FIRE);
    public static final TypeResistBerry PASSHO_BERRY = new TypeResistBerry("Passho", Type.WATER);
    public static final TypeResistBerry RINDO_BERRY = new TypeResistBerry("Rindo", Type.GRASS);
    public static final TypeResistBerry YACHE_BERRY = new TypeResistBerry("Yache", Type.ICE);
    public static final TypeResistBerry CHOPLE_BERRY = new TypeResistBerry("Chople", Type.FIGHTING);
    public static final TypeResistBerry KEBIA_BERRY = new TypeResistBerry("Kebia", Type.POISON);
    public static final TypeResistBerry SHUCA_BERRY = new TypeResistBerry("Shuca", Type.GROUND);
    public static final TypeResistBerry COBA_BERRY = new TypeResistBerry("Coba", Type.FLYING);
    public static final TypeResistBerry PAYAPA_BERRY = new TypeResistBerry("Payapa", Type.PSYCHIC);
    public static final TypeResistBerry TANGA_BERRY = new TypeResistBerry("Tanga", Type.BUG);
    public static final TypeResistBerry CHARTI_BERRY = new TypeResistBerry("Charti", Type.ROCK);
    public static final TypeResistBerry KASIB_BERRY = new TypeResistBerry("Kasib", Type.GHOST);
    public static final TypeResistBerry HABAN_BERRY = new TypeResistBerry("Haban", Type.DRAGON);
    public static final TypeResistBerry COLBUR_BERRY = new TypeResistBerry("Colbur", Type.DARK);
    public static final TypeResistBerry BABIRI_BERRY = new TypeResistBerry("Babiri", Type.STEEL);
    public static final TypeResistBerry CHILAN_BERRY = new TypeResistBerry("Chilan", Type.NORMAL);
    public static final TypeResistBerry ROSELI_BERRY = new TypeResistBerry("Roseli", Type.FAIRY);
}
