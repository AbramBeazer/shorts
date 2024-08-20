package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.types.Type.BUG;
import static org.shorts.model.types.Type.DARK;
import static org.shorts.model.types.Type.DRAGON;
import static org.shorts.model.types.Type.ELECTRIC;
import static org.shorts.model.types.Type.FAIRY;
import static org.shorts.model.types.Type.FIGHTING;
import static org.shorts.model.types.Type.FIRE;
import static org.shorts.model.types.Type.FLYING;
import static org.shorts.model.types.Type.GHOST;
import static org.shorts.model.types.Type.GRASS;
import static org.shorts.model.types.Type.GROUND;
import static org.shorts.model.types.Type.ICE;
import static org.shorts.model.types.Type.NORMAL;
import static org.shorts.model.types.Type.POISON;
import static org.shorts.model.types.Type.PSYCHIC;
import static org.shorts.model.types.Type.ROCK;
import static org.shorts.model.types.Type.STEEL;
import static org.shorts.model.types.Type.WATER;

public class TypeBoostItem extends HeldItem {

    public static final double TYPE_BOOST_ITEM_MULTIPLIER = 1.2;

    private Type type;

    protected TypeBoostItem(String name, int flingPower, Type type) {
        super(name, flingPower);
        this.type = type;
    }

    public static final TypeBoostItem BLACK_GLASSES = new TypeBoostItem("Black Glasses", 30, DARK);
    public static final TypeBoostItem TWISTED_SPOON = new TypeBoostItem("Twisted Spoon", 30, PSYCHIC);
    public static final TypeBoostItem SPELL_TAG = new TypeBoostItem("Spell Tag", 30, GHOST);
    public static final TypeBoostItem MAGNET = new TypeBoostItem("Magnet", 30, ELECTRIC);
    public static final TypeBoostItem CHARCOAL = new TypeBoostItem("Charcoal", 30, FIRE);
    public static final TypeBoostItem MIRACLE_SEED = new TypeBoostItem("Miracle Seed", 30, GRASS);
    public static final TypeBoostItem MYSTIC_WATER = new TypeBoostItem("Mystic Water", 30, WATER);
    public static final TypeBoostItem NEVER_MELT_ICE = new TypeBoostItem("Never-Melt Ice", 30, ICE);
    public static final TypeBoostItem HARD_STONE = new TypeBoostItem("Hard Stone", 100, ROCK);
    public static final TypeBoostItem SOFT_SAND = new TypeBoostItem("Soft Sand", 10, GROUND);
    public static final TypeBoostItem METAL_COAT = new TypeBoostItem("Metal Coat", 30, STEEL);
    public static final TypeBoostItem DRAGON_FANG = new TypeBoostItem("Dragon Fang", 70, DRAGON);
    public static final TypeBoostItem SILK_SCARF = new TypeBoostItem("Silk Scarf", 10, NORMAL);
    public static final TypeBoostItem FAIRY_FEATHER = new TypeBoostItem("Fairy Feather", 10, FAIRY);
    public static final TypeBoostItem BLACK_BELT = new TypeBoostItem("Black Belt", 30, FIGHTING);
    public static final TypeBoostItem SHARP_BEAK = new TypeBoostItem("Sharp Beak", 50, FLYING);
    public static final TypeBoostItem POISON_BARB = new TypeBoostItem("Poison Barb", 70, POISON);
    public static final TypeBoostItem SILVER_POWDER = new TypeBoostItem("Silver Powder", 10, BUG);

    @Override
    public double getMovePowerMultipliers(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        return move.getType() == this.type ? 1.2 : 1;
    }
}
